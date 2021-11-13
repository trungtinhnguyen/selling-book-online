<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 11/11/2021
  Time: 03:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="UTF-8"/>

    <link href="<c:url value="/template/admin/bootstrap/css/styles.css"/>" rel="stylesheet" />
    <script src="<c:url value="/template/font-awesome/all.js"/>" crossorigin="anonymous"></script>
    <title>Lỗi: <dec:title/></title>
</head>
<body>
<div id="layoutError">
    <div id="layoutError_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <dec:body/>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutError_footer">
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
</body>
</html>
