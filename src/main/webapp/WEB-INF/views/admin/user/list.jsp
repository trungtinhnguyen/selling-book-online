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
    <title>Danh sách người dùng</title>
</head>
<body>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Trang chủ/Danh sách người dùng</li>
</ol>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-user me-1"></i>
        Danh sách người dùng
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Địa chỉ</th>
                <th colspan="2">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${model}" var="item">
                <c:url var="editUrl" value="/quan-tri/nguoi-dung/chinh-sua">
                    <c:param name="id" value="${item.id}"/>
                </c:url>
                <tr>
                    <td>${item.fullName}</td>
                    <td>${item.email}</td>
                    <td>${item.tell}</td>
                    <td>${item.address}</td>
                    <td>
                        <div class="nav">
                            <a class="nav-link" href="${editUrl}"><i class="fas fa-edit"></i></a>
                        </div>
                    </td>
                    <td>
                        <div class="nav">
                            <button class="nav-link btn btn-outline-light" onclick="deleteUser(${item.id})">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function deleteUser (id) {
        Swal.fire ({
            title: "Xác nhận",
            text: "Bạn có chắc chắn xóa người dùng này",
            icon: "warning",
            showCancelButton: true,
            cancelButtonColor: '#979797',
            cancelButtonText: "Hủy",
            confirmButtonText: "Xoá",
            confirmButtonColor: '#ff0000'
        }).then(function (result) {
            if (result.isConfirmed) {
                $.ajax({
                    url: '${deleteUserApi}',
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(id),
                    success: function () {
                        let title = "Xóa người dùng thành công!";
                        let type = "success";
                        let text = "Xóa người dùng thành công";
                        let url = window.location.toString();
                        showNotification(title, text, type, url);
                    },
                    error: function (error) {
                        console.log(error);
                    }
                })
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
        })
    }
</script>
</body>
</html>
