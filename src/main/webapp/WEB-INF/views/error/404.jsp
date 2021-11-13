<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 11/11/2021
  Time: 03:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <title>Không tìm thấy trang</title>
</head>
<body>
<div class="text-center mt-4">
    <img class="mb-4 img-error" src="<c:url value="/template/admin/assets/img/error-404-monochrome.svg"/>"/>
    <p class="lead">Không tìm thấy trang. Hãy tìm kiếm nội dung khác trên trang</p>
    <a href="<c:url value="/"/>">
        <i class="fas fa-arrow-left me-1"></i>
        Về trang chủ
    </a>
</div>
</body>
</html>
