<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 17/10/2021
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:url var="deleteApi" value="/api/book"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Quản lý sách</title>
</head>
<body>
<ol class="breadcrumb mb-4">
    <li class="breadcrumb-item active">Trang chủ/Quản lý sách</li>
</ol>

<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-user me-1"></i>
        Danh sách sách
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>STT</th>
                <th>Tên sách</th>
                <th>Thể loại</th>
                <th>Giá</th>
                <th>Ngày nhập</th>
                <th colspan="2">Thao tác</th>
            </tr>
            </thead>
            <tbody>

                <c:forEach var="book" items="${model}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <c:forEach var="category" items="${categories}">
                           <c:if test="${book.categoryCode.equals(category.code)}">
                               <td>${category.name}</td>
                           </c:if>
                        </c:forEach>
                        <td>${book.price}</td>
                        <td>${book.imputedDate}</td>
                        <td>
                            <div class="nav">
                                <a class="nav-link btn btn-outline-light" href="<c:url value="/quan-tri/sach/chinh-sua?id=${book.id}"/>"><i class="fas fa-edit"></i></a>
                            </div>
                        </td>
                        <td>
                            <div class="nav">
                               <button class="nav-link btn btn-outline-light" onclick="deleteBook(${book.id})">
                                   <i class="fas fa-trash"></i>
                               </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">

  function deleteBook(id) {
     Swal.fire({
         title: "Xác nhận xóa",
         text: "Bạn có chắc chắn xóa sản phẩm này",
         icon: "warning",
         showCancelButton: true,
         cancelButtonText: "Hủy",
         cancelButtonColor: "#979797",
         confirmButtonText: "Xóa",
         confirmButtonColor: "#ff0000"
     }).then(function (result) {
         if (result.isConfirmed) {
             $.ajax({
                 url: '${deleteApi}',
                 type: 'DELETE',
                 contentType: 'application/json',
                 data: JSON.stringify(id),
                 success: function () {
                     let title = "Thành công";
                     let text = "Bạn đã xóa thành công";
                     let type = "success";
                     let url = window.location.toString();
                     showNotification(title, text, type, url);
                 },
                 error: function (error) {
                     let title = "Lỗi";
                     let type = "error";
                     let message = "Lỗi hệ thống\nVui lòng thực hiện lại sau";
                     let url = window.location.toString();
                     showNotification(title,message, type, url);
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
