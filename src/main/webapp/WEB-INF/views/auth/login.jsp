<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Đăng nhập</title>
</head>
<body>
<div class="col-lg-5">
    <div class="card shadow-lg border-0 rounded-lg mt-5">
        <div class="card shadow-lg border-0 rounded-lg mt-5">
            <div class="card-header"><h3 class="text-center font-weight-light my-4">Đăng nhập</h3></div>
            <div class="card-body">
                <form action="<c:url value="/j_spring_security_check"/>" method="post" id="formLogin">
                    <c:if test="${param.get('message') != null}">
                        <div class="alert alert-${alert}">
                        <span>${message}</span>
                        </div>
                    </c:if>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="inputUsername" name="j_username" type="text" placeholder="Tên đăng nhập" />
                        <label for="inputUsername">Tên đăng nhập</label>
                    </div>
                    <div class="text m-lg-3 mb-3 p-0">
                        <span id="inputUsernameStatus" class="alert-danger"></span>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="inputPassword" name="j_password" type="password" placeholder="Mật khẩu" />
                        <label for="inputPassword">Mật khẩu</label>
                    </div>
                    <div class="text m-lg-3 mb-3 p-0">
                        <span id="inputPasswordStatus" class="alert-danger"></span>
                    </div>
                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                        <a class="small" href="password.html">Forgot Password?</a>
                        <input class="btn btn-primary" type="submit" id="btn-login" value="Đăng nhập"/>
                    </div>
                </form>
            </div>
            <div class="card-footer text-center py-3">
                <div class="small"><a href="<c:url value="/dang-ky"/>">Bạn chưa có tài khoản, đăng ký ngay?</a></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#btn-login').click (function () {
        validateLoginForm();
    })
</script>
</body>
</html>