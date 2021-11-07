<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:url var="loginPage" value="/dang-nhap"/>
<c:url var="registerPage" value="/dang-ky"/>
<c:url var="createAccountApi" value="/api/user"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Đăng ký</title>
</head>
<body>
<div class="col-lg-7">
    <div class="card shadow-lg border-0 rounded-lg mt-5">
        <div class="card-header"><h3 class="text-center font-weight-light my-4">Đăng ký</h3></div>
        <div class="card-body">
            <form id="formRegister">
                <div class="form-floating mb-2">
                    <input class="form-control" id="inputFullName" name="fullName" type="text" placeholder="Nhập họ và tên" />
                    <label for="inputFullName">Họ tên</label>
                </div>
                <div class="m-lg-3 mb-2">
                    <span class="alert-danger" id="inputFullNameStatus"></span>
                </div>
                <div class="form-floating mb-2">
                    <input class="form-control" id="inputUsername" name="username" type="text" placeholder="Tên đăng nhập" />
                    <label for="inputUsername">Tên đăng nhập</label>
                </div>
                <div class="m-lg-3 mb-2">
                    <span class="alert-danger" id="inputUsernameStatus"></span>
                </div>
                <div class="form-floating mb-2">
                    <input class="form-control" id="inputEmail" name="email" type="email" placeholder="name@example.com" />
                    <label for="inputEmail">Email</label>
                </div>
                <div class="m-lg-3 mb-2">
                    <span class="alert-danger" id="inputEmailStatus"></span>
                </div>
                <div class="row mb-2">
                    <div class="col-md-6">
                        <div class="form-floating mb-2 mb-md-0">
                            <input class="form-control" id="inputPassword" name="password" type="password" placeholder="Điền mật khẩu"/>
                            <label for="inputPassword">Mật khẩu</label>
                        </div>
                        <div class="m-lg-3 mb-2">
                            <span class="alert-danger" id="inputPasswordStatus"></span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating mb-2 mb-md-0">
                            <input class="form-control" id="inputPasswordConfirm" type="password" placeholder="Nhập lại mật khẩu" />
                            <label for="inputPasswordConfirm">Nhập lại mật khẩu</label>
                        </div>
                        <div class="m-lg-3 mb-2">
                            <span class="alert-danger" id="inputPasswordConfirmStatus"></span>
                        </div>
                    </div>
                </div>
                <div class="form-floating mb-2 mb-md-0">
                    <input class="form-control" id="inputTell" name="tell" type="text" placeholder="Số điện thoại" />
                    <label for="inputTell">Số điện thoại</label>
                </div>
                <div class="m-lg-3 mb-2">
                    <span class="alert-danger" id="inputTellStatus"></span>
                </div>
                <div class="form-floating mb-2 mb-md-0">
                    <input class="form-control" id="inputAddress" name="address" type="text" placeholder="Địa chỉ" />
                    <label for="inputAddress">Địa chỉ</label>
                </div>
                <div class="m-lg-3 mb-2">
                    <span class="alert-danger" id="inputAddressStatus"></span>
                </div>
                <div class="mt-4 mb-0">
                    <div class="d-grid">
                        <input class="btn btn-primary" type="submit" value="Đăng ký" id="btn-register"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="card-footer text-center py-3">
            <div class="small"><a href="<c:url value="/dang-nhap"/>">Đã có tài khoản, Đăng nhập ngay</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">

    $('#btn-register').click (function (e) {
        e.preventDefault();
       let isCompleted = validateRegisterForm();
       if (isCompleted) {
           let data = {};
           let formData = $('#formRegister').serializeArray();
           $.each(formData, function (index, value) {
               if (value.name !== '') {
                data[""+value.name+""] = value.value;
               }
           })
           register(data);
       }
    });

    function register(data) {
        $.ajax ({
            url: '${createAccountApi}',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: "json",
            success: function (result) {
                let title, message, type, url;
                message = result.message;
                type = result.type;
                if (result.success) {
                    title = "Đăng ký tài khoản thành công";
                    url = '${loginPage}';
                } else {
                    title = "LỖI";
                    url = window.location.toString();
                }
                showNotification(title, message, type, url);
            },
            error: function (error) {
                let title = "ĐĂNG KÝ KHÔNG THÀNH CÔNG";
                let message =  "Đã xãy ra lỗi, Vui lòng kiểm tra lại"
                let type="error";
                let url = document.documentURI;
                showNotification(title, message, type, url);
            }
        });
    }
    function showNotification(title, message, type, url) {
        Swal.fire({
            title: title,
            text: message,
            icon: type,
            confirmButtonText: "Đóng",
            closeOnConfirm: true,
            allowEscapeKey: false,
            allowOutsideClick: false
        }).then(function (result) {
            if (result.isConfirmed) {
                window.location.href = url;
            }
        });
    }

</script>
</body>
</html>
