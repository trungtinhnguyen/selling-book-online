<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 29/09/2021
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.example.util.SecurityUtils"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><dec:title default="Quản trị"/> </title>

    <link href="<c:url value="/template/admin/bootstrap/css/simple-datatable.css"/> ">
    <link href="<c:url value="/template/admin/bootstrap/css/styles.css"/>" rel="stylesheet" />
    <script src="<c:url value="/template/jquery/jquery-3.2.1.js"/>"></script>
    <script src="<c:url value="/template/font-awesome/all.js"/>" crossorigin="anonymous"></script>
    <script src="<c:url value="/template/sweet-alert/dist/sweetalert2.all.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/template/sweet-alert/dist/sweetalert2.min.css"/>"/>
</head>
<body class="sb-nav-fixed">
<%@ include file="/common/admin/header.jsp"%>
<div id="layoutSidenav">
    <%@ include file="/common/admin/sidenav.jsp"%>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Dashboard</h1>
                <dec:body/>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2021</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>

<script src="<c:url value="/template/admin/bootstrap/js/bootstrap.bundle.min.js"/>" crossorigin="anonymous"></script>
<script src="<c:url value="/template/admin/bootstrap/js/scripts.js"/>"></script>
<script src="<c:url value="/template/admin/bootstrap/js/simple-datatables.js"/>" crossorigin="anonymous"></script>
<script src="<c:url value="/template/admin/bootstrap/js/datatables-simple-demo.js"/>"></script>
</body>
</html>
