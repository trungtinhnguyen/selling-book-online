<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 27/09/2021
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Trang chủ</title>
</head>
<body>
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-end mt-2 mb-1">
        <div class="col-lg-4">
            <div class="w-100">
               <form action="<c:url value="/tim-kiem"/>" method="get">
                   <input type="text" name="search-text" class="nav-item form-control d-inline w-75" placeholder="Tìm kiếm"/>
                   <button class="btn btn-none-border" id="btn-search" type="submit"><i class="fas fa-search"></i></button>
                   <input type="hidden" name="page" id="page" value="0"/>
                   <input type="hidden" name="size" id="size" value="20"/>
               </form>
            </div>
        </div>
    </div>
    <!-- Heading Row-->
    <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="${bestSeller.thumbnail}" alt="${bestSeller.name}" /></div>
        <div class="col-lg-5">
            <h1 class="font-weight-light">${bestSeller.name}</h1>
            <p>${bestSeller.description}</p>
            <a class="btn btn-primary" href="<c:url value="/sach?id=${bestSeller.id}"/>">Chi tiết</a>
        </div>
    </div>
    <!-- Call to Action-->
    <div class="card text-white bg-secondary my-5 py-4 text-center">
        <div class="card-body"><p class="text-white m-0">This call to action card is a great place to showcase some important information or display a clever tagline!</p></div>
    </div>
    <!-- Content Row-->
    <div class="row gx-4 gx-lg-5">
        <c:forEach var="book" items="${model}">
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
</div>
</body>
</html>
