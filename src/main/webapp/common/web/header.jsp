<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container px-5">
    <a class="navbar-brand" href="#!">IT Book Store</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
        <security:authorize access="isAuthenticated()">
          <li class="nav-item">
            <span class="nav-link disabled">Xin chào, <%=SecurityUtils.getPrincipal().getFullName()%></span>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/dang-xuat"/>">Đăng xuất</a>
          </li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/dang-nhap"/>">Đăng nhập</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/dang-ky"/>">Đăng ký</a>
          </li>
        </security:authorize>
      </ul>
    </div>
  </div>
</nav>
