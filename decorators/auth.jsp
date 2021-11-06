<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 27/09/2021
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><dec:title default="Login"/></title>

    <!-- bootstrap core -->
    <link href="<c:url value="/template/admin/bootstrap/css/styles.css"/>" rel="stylesheet" />
    <!-- jquery core -->
    <script src="<c:url value="/template/jquery/jquery-3.2.1.js"/> "></script>
    <script src="<c:url value="/template/font-awesome/all.js"/>" crossorigin="anonymous"></script>
    <script src="<c:url value="/template/sweet-alert/dist/sweetalert2.all.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/template/sweet-alert/dist/sweetalert2.min.css"/>"/>
</head>
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                            <dec:body/>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">
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
<script src="<c:url value="/template/auth/js/script.js"/>"></script>
</body>
</html>
