<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 06/10/2021
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:url var="deleteUserApi" value="/api/user"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Chi tiết hóa đơn</title>
</head>
<body>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Chi tiết hóa đơn số </li>
</ol>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-receipt me-1"></i>
        Mã hóa đơn: ${model.id}
    </div>
    <div class="card-body">
        <div class="row align-items-start mb-5">
           <div class="col-lg-8">
               <table class="table">
                   <tr>
                       <th>Họ tên:</th>
                       <td>${user.fullName}</td>
                   </tr>
                   <tr>
                       <th>Địa chỉ:</th>
                       <td>${user.address}</td>
                   </tr>
                   <tr>
                       <th>Số điện thoại:</th>
                       <td>${user.tell}</td>
                   </tr>
               </table>
           </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="2">Mã hóa đơn:</th>
                <td colspan="3">${model.id}</td>
            </tr>
            <tr>
                <th colspan="2">Ngày tạo:</th>
                <td colspan="3">${model.modifiedDate}</td>
            </tr>
            <tr>
                <th>STT</th>
                <th>Tên SP</th>
                <th>Đơn giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
            </tr>
            </thead>
            <tbody>
            <%! int i = 1; %>
            <c:forEach items="${model.details}" var="item">
                <tr>
                    <td><%out.print(i++);%></td>
                    <td>${item.book.name}</td>
                    <td>${item.book.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.prices}</td>
                </tr>
            </c:forEach>
            <%i = 1;%>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="2">Tổng:</th>
                <td colspan="3" class="text-danger text-end"><span class="m-lg-5">${model.totalPrice}</span></td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>
