<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 30/09/2021
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.example.util.SecurityUtils"%>
<html>
<head>
    <title><dec:title default="Trang chủ"/> </title>

    <!-- css -->
    <link rel="stylesheet" href="<c:url value="/template/web/css/style.css"/>"/>
    <!-- Playfair font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="<c:url value='/template/web/bootstrap/assets/favicon.ico'/>" />
    <!-- JQUERY CORE -->
    <script src="<c:url value="/template/jquery/jquery-3.2.1.js"/>"></script>
    <!-- Font Awesome -->
    <script src="<c:url value="/template/font-awesome/all.js"/>"></script>
    <!-- Sweet alert -->
    <script src="<c:url value="/template/sweet-alert/dist/sweetalert2.all.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/template/sweet-alert/dist/sweetalert2.min.css"/>"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="<c:url value='/template/web/bootstrap/css/styles.css'/>" rel="stylesheet" />
</head>
<body>
    <!-- Responsive navbar-->
    <%@ include file="/common/web/header.jsp"%>
    <!-- Page Content-->
    <dec:body/>
    <!-- Footer-->
    <%@ include file="/common/web/footer.jsp"
    %>
<!-- Bootstrap core JS-->
<script src="<c:url value='/template/web/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
<!-- Core theme JS-->
<script src="<c:url value='/template/web/bootstrap/js/scripts.js'/>"></script>
</body>
</html>
