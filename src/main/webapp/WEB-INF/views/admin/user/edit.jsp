<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 06/10/2021
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:url value="/api/user" var="editApi"/>
<c:url var="$editUrl" value="/quan-tri/nguoi-dung/chinh-sua"/>
<c:url value="/template" var="template"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Cập nhật thông tin người dùng</title>
</head>
<body>

<ol class="breadcrumb mb-4">
    <c:if test="${not empty model.id}">
        <li class="breadcrumb-item active">Trang chủ / Người dùng / ${model.fullName}</li>
    </c:if>
    <c:if test="${empty model.id}">
       <li class="breadcrumb-item active">Trang chủ / Người dùng / Thêm người dùng</li>
    </c:if>
</ol>

<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-user-edit me-1"></i>
        Cập nhật thông tin người dùng
    </div>
    <div class="card-body">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <form:form role="form" id="formSubmit" modelAttribute="model">
                        <div class="form-group mb-3">
                            <label for="inputFullName">Họ và tên</label>
                            <form:input path="fullName" id="inputFullName" cssClass="form-control"/>
                            <span class="alert-danger" id="inputFullNameStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputEmail">Email</label>
                            <form:input path="email" id="inputEmail" cssClass="form-control"/>
                        </div>
                        <c:if test="${empty model.id}">
                            <div class="form-group mb-3">
                                <label for="inputUsername">Tên đăng nhập</label>
                                <input id="inputUsername" class="form-control" name="username"/>
                                <span class="alert-danger" id="inputUsernameStatus"></span>
                            </div>
                            <div class="form-group mb-3">
                                <label for="inputPassword">Mật khẩu</label>
                                <input type="text" id="inputPassword" class="form-control" name="password"/>
                                <span class="alert-danger" id="inputPasswordStatus"></span>
                            </div>
                        </c:if>
                        <div class="form-group mb-3">
                            <label for="inputTell">Số điện thoại</label>
                            <form:input path="tell" id="inputTell" cssClass="form-control"/>
                            <span class="alert-danger" id="inputTellStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputAddress">Địa chi</label>
                            <form:input path="address" id="inputAddress" cssClass="form-control"/>
                            <span class="alert-danger" id="inputAddressStatus"></span>
                        </div>
                        <div class="row mb-3">
                            <label>Vai trò</label>
                            <div class="d-flex flex-wrap m-3 justify-content-start">
                                <c:forEach items="${roles}" var="role">
                                   <input class="p-3 m-2 mt-1" type="checkbox" name="roles" id="checkBox_${role.id}"
                                          value="${role.code}"
                                          <c:if test="${model.roles.contains(role.code)}">checked</c:if>
                                   />
                                   <label for="checkBox_${role.id}">${role.name}</label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-4">
                                    <c:if test="${empty model.id}">
                                        <input id="btn-create-update" type="submit" class="btn btn-warning form-control" value="Thêm tài khoản"/>
                                    </c:if>
                                    <c:if test="${not empty model.id}">
                                        <input id="btn-create-update" type="submit" class="btn btn-warning form-control" value="Cập nhật"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="modelId" value="${model.id}"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/template/auth/js/script.js"/>"></script>
<script type="text/javascript">
    let id = $('#modelId').val();
    $('#btn-create-update').click (function (e) {
        e.preventDefault();
        let completed = validateRegisterForm();
        if (completed) {
            let data = {};
            let roles;
            let formDate = $('#formSubmit').serializeArray();
            $.each(formDate, function (i, v) {
                data[""+v.name+""] = v.value;
            })
            roles = $('#formSubmit input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            data["id"] = id;
            data["roleCodes"] = roles;
            if (id === '') {
                createAccount(data);
            } else {
                updateAccount(data);
            }
        }
    })

    function createAccount (data) {
        $.ajax({
            url: '${editApi}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                let message = result.message;
                let type = result.type;
                let title;
                let url = "${$editUrl}";
                if (result.success) {
                    title = "Thành công";
                    url += '?id='+result.id;
                } else {
                    title = "Lỗi";
                }
                showNotification (title, message, type, url);
            },
            error: function (error) {
                showError();
            }
        })
    }

    function updateAccount(data) {
        $.ajax({
            url: '${editApi}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                let message = result.message;
                let type = result.type;
                let url = "${$editUrl}?id="+result.id;
                let title;
                if (result.success) {
                    title = "Thành công";
                } else {
                    title = "Lỗi";
                }
                showNotification (title, message, type, url);
            },
            error: function (error) {
                showError();
            }
        })
    }

    function showError () {
        let title = "ĐÃ XÃY RA LỖI";
        let message = "Vui lòng thực hiện lại sau!"
        let type = "error";
        let url ='${$editUrl}?id='+id;
        showNotification(title, message, type, url);
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
        })
    }

    $('#inputFullName').onload = function (){

    }
</script>

</body>
</html>
