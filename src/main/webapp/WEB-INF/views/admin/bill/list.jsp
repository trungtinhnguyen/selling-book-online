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
    <title>Danh sách hóa đơn</title>
</head>
<body>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Trang chủ/Danh sách hóa đơn</li>
</ol>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-receipt me-1"></i>
        Danh sách hóa đơn
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>Mã HĐ</th>
                <th>Người dùng</th>
                <th>Ngày tạo</th>
                <th>Tổng tiền</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${model}" var="item">
                <c:url var="detailUrl" value="/quan-tri/hoa-don/chi-tiet">
                    <c:param name="id" value="${item.id}"/>
                </c:url>
                <tr>
                    <td>${item.id}</td>
                    <td>${item.username}</td>
                    <td>${item.modifiedDate}</td>
                    <td>${item.totalPrice}</td>
                    <td>
                        <div class="nav">
                            <a class="nav-link" href="${detailUrl}"><i class="fas fa-info-circle"></i> Chi tiết</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
