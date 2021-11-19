<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 19/11/2021
  Time: 01:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Tìm Kiếm</title>
</head>
<body>
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 align-items-center my-5">
            <!-- Content Row-->
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <c:forEach var="book" items="${model}">
                    <div class="col-md-10 mb-5">
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
    </div>
</body>
</html>
