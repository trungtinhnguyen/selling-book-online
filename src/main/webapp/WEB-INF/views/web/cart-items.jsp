<%@ include file="/common/taglibs.jsp"%>
<c:url var="cartApi" value="/api/cart"/>
<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 12/11/2021
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Giỏ hàng</title>
</head>
<body>
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <div class="col-lg-5">
                <h1><span><i class="fas fa-shopping-cart"></i> </span> Giỏ hàng</h1>
            </div>
        </div>
        <div class="row gx-4 gx-lg-5">
            <c:if test="${model.size() == 0}">
                <span>Chưa có sản phầm nào trong giỏ hàng</span>
                <a href="/trang-chu"><span>Tiếp tục mua hàng</span></a>
            </c:if>
            <c:if test="${model.size() > 0}">
                <table class="table">
                    <thead>
                    <th colspan="2">
                        <div class="check-box-container">
                            <input id="check-all" type="checkbox" class="check-box"/>
                            <label for="check-all"><span>Chọn tất cả</span></label>
                        </div>
                    </th>
                    <th>Tên sách</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${model}" var="item">
                        <tr>
                            <td>
                                <div class="check-box-container">
                                    <input class="check-box" type="checkbox" id="checkbox_${item.id}"/>
                                    <span class="check-mark"></span>
                                </div>
                            </td>
                            <td>
                                <img class="img-cart" src="${item.book.thumbnail}"/>
                            </td>
                            <td>${item.book.name}</td>
                            <td>
                                <div class="nav navbar-brand">
                                    <a class="nav-link text-black" onclick="toDown(${item.id})"><i class="far fa-minus-square"></i></a>
                                    <span id="quantity_${item.id}" class="nav-item">${item.quantity}</span>
                                    <a class="nav-link text-black" onclick="toUp(${item.id})"><i class="far fa-plus-square"></i></a>
                                </div>
                            </td>
                            <td>
                                <span id="price_${item.id}">${item.prices}</span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
      <c:if test="${model.size() > 0}">
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
      </c:if>

    </div>
    <script type="text/javascript">
        function toDown (id) {
          let url = '${cartApi}?type=down';
          save(id, url);
        }
        function toUp (id) {
            let url = '${cartApi}?type=up';
            save(id, url)
        }
        function save(id, url) {
            $.ajax({
                url: url,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(id),
                dataType: 'json',
                success: function (result) {
                    if(result.id === null) {
                        location.reload();
                    } else {
                        const checkbox = $('#checkbox_'+id)[0];
                        const price = $('#price_'+id);
                        if (checkbox.checked) {
                            let change = parseFloat(result.prices) - parseFloat(price.text());
                            totalPrices.text(parseFloat(totalPrices.text()) + change);
                        }
                        $('#quantity_'+id).text(result.quantity);
                        price.text(result.prices);
                    }
                },
                error: (error) => console.log(error)
            })
        }
        let totalPrices = $('#totalPrices');
        const check_boxes = $('tbody input[type=checkbox]');
        let i;
        for (i = 0; i < check_boxes.length; i++) {
            check_boxes[i].addEventListener('change', (event) => {
                let id = event.currentTarget.id.replace('checkbox', 'price');
                let current = parseFloat(totalPrices.text());
                let price = parseFloat($('#'+id).text());
                if (event.currentTarget.checked) {
                    totalPrices.text(current + price);
                } else {
                    totalPrices.text(current - price);
                }
            })
        }

        $('#check-all')[0].addEventListener('change', (event) => {
            let current = parseFloat($('#totalPrices').text());
            let total = 0.0;
            if (event.currentTarget.checked) {
                for (i = 0; i < check_boxes.length; i++) {
                    if (!check_boxes[i].checked) {
                        check_boxes[i].checked = true;
                        let id = '#' + check_boxes[i].id.replace('checkbox', 'price');
                        total += parseFloat($(id).text());
                    }
                }
            } else {
                for (i = 0; i < check_boxes.length; i++) {
                    if (check_boxes[i].checked) {
                        check_boxes[i].checked = false;
                        let id = '#' + check_boxes[i].id.replace('checkbox', 'price');
                        total -= parseFloat($(id).text());
                    }
                }
            }
            totalPrices.text(current + total);
        })
        $('#btn-order').click(function (event) {
            let items = '';
            event.preventDefault();
            for (i = 0; i < check_boxes.length; i++) {
                if (check_boxes[i].checked) {
                    let id = check_boxes[i].id;
                    items += id.slice(id.indexOf('_')+1, id.length);
                    items += ',';
                }
            }
            if (items !== '') {
                let url = '/dat-hang?items-id='+items.substring(0, items.length-1);
                window.location = url;
            }
        });
    </script>
</body>
</html>
