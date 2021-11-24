<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 13/11/2021
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<c:url var="payApi" value="/api/payment"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Xác Nhận Đặt Hàng</title>
</head>
<body>
<div class="container px-4 px-lg-5">
  <div class="row gx-4 gx-lg-5 align-items-center my-5">
    <div class="col-lg-5">
      <h1><span><i class="fas fa-cart-arrow-down"></i> </span> Đơn hàng</h1>
    </div>
  </div>
  <div class="row gx-4 gx-lg-5 mb-5">
    <table class="table">
      <thead>
      <th></th>
      <th>Tên sách</th>
      <th>Số lượng</th>
      <th>Giá</th>
      </thead>
      <tbody>
      <c:forEach items="${model}" var="item">
        <tr>
          <input type="hidden" id="id_${item.id}"/>
          <td>
            <img class="img-cart" src="${item.book.thumbnail}"/>
          </td>
          <td>${item.book.name}</td>
          <td>
            <div class="nav navbar-brand">
              <span id="quantity_${item.id}" class="nav-item">${item.quantity}</span>
            </div>
          </td>
          <td>
            <span id="price_${item.id}">${item.prices}</span>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="row gx-4 gx-lg-5 mb-5 mt-3 justify-content-center">
    <h3><span><i class="far fa-address-card  me-2"></i> </span>Xác nhận thông tin giao hàng</h3>
    <table class="col-lg-10 mt-3">
      <tr>
        <th>Họ tên:</th>
        <td><span>${user.fullName}</span></td>
        <td><a class="nav-link" href="#"><i class="fas fa-edit"></i> </a></td>
      </tr>
      <tr>
        <th>Địa chỉ nhận hàng:</th>
        <td><span>${user.address}</span></td>
        <td><a class="nav-link" href="#"><i class="fas fa-edit"></i> </a></td>
      </tr>
      <tr>
        <th>Số điện thoại nhận hàng:</th>
        <td><span>${user.tell}</span></td>
        <td><a class="nav-link" href="#"><i class="fas fa-edit"></i> </a></td>
      </tr>
    </table>
    <input type="hidden" id="size" value="${model.size()}">
  </div>
  <div class="row gx-4 gx-lg-5">
    <div class="col-lg-8 align-items-center">
    </div>
    <div class="col-lg-4 card">
      <div class="card-header">
        <h3>Đặt hàng</h3>
      </div>
      <div class="card-body">
        Tổng tiền: <span id="totalPrices">0</span>
        <span id="unitPrice">VNĐ</span>
      </div>
      <div class="card-footer">
        <button class="btn btn-danger" id="btn-order">Đặt hàng</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  let total = 0.0;
  const size = parseInt($('#size').val());
  const items = $('table input[type=hidden]');
  let itemIds = [];
  for (let i = 0; i < size; i++) {
    let id = items[i].id;
    total+= parseFloat($('#'+id.replace('id', 'price')).text());
    itemIds[i] = id.slice(id.indexOf('_')+1, id.length);
  }
  const totalPrices = $('#totalPrices');
  totalPrices.text(total);

  $('#btn-order').click ((event) => {
    let data ={};
    data['itemIds'] = itemIds;
    event.preventDefault();
    $.ajax({
      url: '${payApi}',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(data),
      dataType: 'json',
      success: (result) => {
        if (result.success === true) {
          location = '/thong-tin-don-hang?id='+result.id;
        }
      },
      error: (error) => {
        console.log(error);
    }
    })
  })
</script>
</body>
</html>
