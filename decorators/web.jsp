<%--
  Created by IntelliJ IDEA.
  User: tinhnguyen
  Date: 30/09/2021
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.example.util.SecurityUtils"%>
<html>
<head>
    <title><dec:title default="Trang chủ"/> </title>

  <!-- css -->
  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="<c:url value='/template/web/bootstrap/assets/favicon.ico'/>" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="<c:url value='/template/web/bootstrap/css/styles.css'/>" rel="stylesheet" />
</head>
<body>
<!-- Responsive navbar-->
<%@ include file="/common/web/header.jsp"%>
<!-- Page Content-->
<div class="container px-4 px-lg-5">
  <!-- Heading Row-->
  <div class="row gx-4 gx-lg-5 align-items-center my-5">
    <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="https://dummyimage.com/900x400/dee2e6/6c757d.jpg" alt="..." /></div>
    <div class="col-lg-5">
      <h1 class="font-weight-light">Business Name or Tagline</h1>
      <p>This is a template that is great for small businesses. It doesn't have too much fancy flare to it, but it makes a great use of the standard Bootstrap core components. Feel free to use this template for any project you want!</p>
      <a class="btn btn-primary" href="#!">Call to Action!</a>
    </div>
  </div>
  <!-- Call to Action-->
  <div class="card text-white bg-secondary my-5 py-4 text-center">
    <div class="card-body"><p class="text-white m-0">This call to action card is a great place to showcase some important information or display a clever tagline!</p></div>
  </div>
  <!-- Content Row-->
  <div class="row gx-4 gx-lg-5">
    <div class="col-md-4 mb-5">
      <div class="card h-100">
        <div class="card-body">
          <h2 class="card-title">Card One</h2>
          <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem magni quas ex numquam, maxime minus quam molestias corporis quod, ea minima accusamus.</p>
        </div>
        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
      </div>
    </div>
    <div class="col-md-4 mb-5">
      <div class="card h-100">
        <div class="card-body">
          <h2 class="card-title">Card Two</h2>
          <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod tenetur ex natus at dolorem enim! Nesciunt pariatur voluptatem sunt quam eaque, vel, non in id dolore voluptates quos eligendi labore.</p>
        </div>
        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
      </div>
    </div>
    <div class="col-md-4 mb-5">
      <div class="card h-100">
        <div class="card-body">
          <h2 class="card-title">Card Three</h2>
          <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem magni quas ex numquam, maxime minus quam molestias corporis quod, ea minima accusamus.</p>
        </div>
        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>
      </div>
    </div>
  </div>
</div>
<!-- Footer-->
<%@ include file="/common/web/footer.jsp"
%>
<!-- Bootstrap core JS-->
<script src="<c:url value='/template/web/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
<!-- Core theme JS-->
<script src="<c:url value='/template/web/bootstrap/js/scripts.js'/>"></script>
</body>
</html>
