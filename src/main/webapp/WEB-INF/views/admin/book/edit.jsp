<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 06/10/2021
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:url value="/api/book" var="editApi"/>
<c:url var="$editUrl" value="/quan-tri/sach/chinh-sua"/>
<c:url value="/template" var="template"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>
        <c:if test="${not empty model.id}">Cập nhật thông tin sản phẩm</c:if>
        <c:if test="${empty model.id}">Thêm sản phẩm mới</c:if>
    </title>
</head>
<body>

<ol class="breadcrumb mb-4">
    <c:if test="${not empty model.id}">
        <li class="breadcrumb-item active">Trang chủ / Sách / ${model.name}</li>
    </c:if>
    <c:if test="${empty model.id}">
       <li class="breadcrumb-item active">Trang chủ / Sách / Thêm mới</li>
    </c:if>
</ol>

<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-user-edit me-1"></i>
        <c:if test="${not empty model.id}">Cập nhật thông tin sản phẩm</c:if>
        <c:if test="${empty model.id}">Thêm mới sản phẩm</c:if>

        <span id="result"></span>
    </div>
    <div class="card-body">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <form:form role="form" id="formSubmit" modelAttribute="model">
                        <div class="form-group row mb-3">
                            <label for="categoryCode">Thể Loại</label>
                            <form:select path="categoryCode" id="categoryCode" cssClass="form-control text-center">
                                <form:option value="" label="--- Thể loại ---"/>
                                <form:options items="${categories}" itemLabel="name" itemValue="code"/>
                            </form:select>
                        </div>
                        <div class="form-group row mb-3">
                            <label for="publisherCode">Nhà Xuất Bản</label>
                            <form:select path="publisherCode" id="publisherCode" cssClass="form-control text-center">
                                <form:option value="">--- Nhà xuất bản ---</form:option>
                                <form:options items="${publishers}" itemValue="code" itemLabel="name"/>
                            </form:select>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputName">Tên sách</label>
                            <form:input path="name" id="inputName" cssClass="form-control"/>
                            <span class=" alert-danger" id="inputNameStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputThumbnail">Hình đại diện</label>
                            <input type="file" id="inputThumbnail" onchange="encodeImageByBase64()" class="form-control"/>
                            <span class=" alert-danger" id="inputThumbnailStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputCover">Hình thức bìa</label>
                            <form:input path="cover" id="inputCover" cssClass="form-control"/>
                            <span class=" alert-danger" id="inputCoverStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputPageNumber">Số trang</label>
                            <form:input path="pageNumber" id="inputPageNumber" cssClass="form-control"/>
                            <span class="alert-danger" id="inputPageNumberStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputPublishedYear">Năm xuất bản</label>
                            <form:input path="publishedYear" id="inputPublishedYear" cssClass="form-control"/>
                            <span class="alert-danger" id="inputPublishedYearStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputPrice">Giá bán</label>
                            <form:input path="price" id="inputPrice" cssClass="form-control"/>
                            <span class="alert-danger" id="inputPriceStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="inputQuantity">Số lượng nhập</label>
                            <form:input path="quantity" id="inputQuantity" cssClass="form-control"/>
                            <span class="alert-danger" id="inputQuantityStatus"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="description">Mô tả</label>
                            <form:textarea path="description" id="description" cssClass="form-control" cols="20" rows="5"/>
                            <span class="alert-danger" id="descriptionStatus"></span>
                        </div>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-4">
                                    <c:if test="${empty model.id}">
                                        <input id="btn-create-update" type="submit" class="btn btn-warning form-control" value="Thêm sản "/>
                                    </c:if>
                                    <c:if test="${not empty model.id}">
                                        <input id="btn-create-update" type="submit" class="btn btn-warning form-control" value="Cập nhật"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="modelId" value="${model.id}"/>
                        <input type="hidden" id="thumbnailString"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/template/admin/js/book.js"/>"></script>
<script type="text/javascript">
    let id = $('#modelId').val();
    $('#btn-create-update').click (function (e) {
        e.preventDefault();
        if (validateBookInfo()) {
            let data = {};
            let thumbnail = $('#thumbnailString').val();
            let formDate = $('#formSubmit').serializeArray();
            $.each(formDate, function (i, v) {
                data[""+v.name+""] = v.value;
            })
            data["id"] = id;
            data["thumbnail"] = thumbnail;
            if (id === '') {
                addBook(data);
            } else {
                updateBook(data);
            }
        }
    })

    function loadThumbnail () {
        const file = $('#inputThumbnail');
        if (!file.prop('files')[0]) {
            alert("Let choose a image!");
        } else {
            return file.prop('files')[0];
        }
    }
    function encodeImageByBase64 () {
        const image = loadThumbnail();
        const reader = new FileReader();
        reader.onload = function (e) {
            $('#thumbnailString').val(e.target.result);
            // $('#thumbnailString').val(e.target.result.replace('data:', '').replace(/^.+,/, ''));
        }
        reader.readAsDataURL(image);
    }

    function addBook (data) {
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

    function updateBook(data) {
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
</script>
</body>
</html>
