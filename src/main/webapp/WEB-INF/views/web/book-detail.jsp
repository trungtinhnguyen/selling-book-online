<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<c:url var="buyUrl" value="/api/cart"/>
<c:url var="cartUrl"  value="/gio-hang"/>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>${model.name}</title>
    </head>
    <body>

    <div class="container">
    <!-- Portfolio Item Heading -->
    <h1 class="my-4">
        <small>${model.name}</small>
    </h1>
    <!-- Portfolio Item Row -->
    <div class="row">
        <div class="col-md-6">
            <img class="img-fluid item-img" src="${model.thumbnail}" alt="${model.name}">
        </div>
        <div class="col-md-6">
            <h3 class="my-3">Giá</h3>
            <span class="text-danger price">${model.price} VNĐ</span>
            <h3 class="my-3">Thông tin chi tiết</h3>
            <ul class=" list-unstyled">
                <table class="table">
                    <tr>
                        <td><li>Nhà xuất bản:</li></td>
                        <td><span>${map.get(model.publisherCode)}</span></td>
                    </tr>
                    <tr>
                        <td><li>Thể loại: </li></td>
                        <td><span>${map.get(model.categoryCode)}</span></td>
                    </tr>
                    <tr>
                        <td><li>Số trang: </li></td>
                        <td><span>${model.pageNumber}</span></td>
                    </tr>
                        <td><li>Bìa:</li></td>
                    <td><span>${model.cover}</span></td>
                    <tr>
                        <td><li>Năm xuất bản: </li></td>
                        <td><span>${model.publishedYear}</span></td>
                    </tr>
                </table>
            </ul>
            <security:authorize access="isAuthenticated()">
                <button class="btn btn-danger" onclick="addCart(${model.id})">MUA</button>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <button class="btn btn-danger" onclick="login(${model.id})">MUA</button>
            </security:authorize>
        </div>

    </div>
    <!-- /.row -->

    <c:if test="${related.size() > 0}">
        <!-- Related Projects Row -->
        <h3 class="my-4">Cùng thể loại</h3>
        <div class="row">
            <c:forEach var="book" items="${related}">
                <div class="col-md-6 mb-5">
                    <div class="card h-100">
                        <div class="card-body container">
                            <div class="row align-items-start">
                                <div class="col-lg-4">
                                    <img class="img-fluid" src="${book.thumbnail}"/>
                                </div>
                                <div class="col-lg-8">
                                    <h2 class="card-title">${book.name}</h2>
                                    <p class="card-text">${book.description}</p>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="<c:url value="/sach?id=${book.id}"/>">Chi tiết</a></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
    <!-- /.row -->

    </div>
    <!-- /.container -->


    <script type="text/javascript">
        let currentUrl = window.location.toString();
        let id = '${model.id}';
        function addCart(bookid) {
            $.ajax({
                url: '${buyUrl}',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(bookid),
                dataType: 'json',
                success: function (result) {
                    let message = result.message;
                    let type = result.type;
                    let title;
                    let url;
                    if (result.success) {
                        title = "Thành công";
                        url = '${cartUrl}';
                    } else {
                        title = "Lỗi";
                        url = currentUrl;
                    }
                    showNotification (title, message, type, url);
                },
                error: function (error) {
                    showError();
                }
            })
        }

        function login (id) {
            Swal.fire({
                title: "Đăng Nhập",
                html: `<form action="<c:url value="/j_spring_security_check"/>" method="POST" id="formLogin">
                        <input class="swal2-input" id="inputUsername" name="j_username" type="text" placeholder="Tên đăng nhập" />
                        <input class="swal2-input" id="inputPassword" name="j_password" type="password" placeholder="Mật khẩu" />
                        </form>`,
                confirmButtonText: "Đăng nhập",
                focusConfirm: false,
                showCancelButton: true,
                cancelButtonText: "Huỷ",
                preConfirm: ()=>{
                    const username = Swal.getPopup().querySelector('#inputUsername').value;
                    const password = Swal.getPopup().querySelector('#inputPassword').value;
                    if (!username || !password) {
                        Swal.validationMessage(`Vui lòng nhập tên đăng nhập và mật khẩu`);
                    }
                },
            }).then(result => {
               if (result.isConfirmed) {
                   $('#formLogin').submit();
               }
            });
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
    </script>
    </body>
</html>