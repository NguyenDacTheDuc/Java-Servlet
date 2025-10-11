<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <link rel="stylesheet" href="../css/CHome.css">
</head>
<style>
  body {
    background-image: url("../picture/lagque.jpg");
    background-size: cover;
  }
  .la:hover{
    background: linear-gradient(to right, blue, red);
    border-radius: 25px;
  }
</style>
<body>
  <div class="container">
    <div class="col-12 bg-white">
      <jsp:include page="Navbar.jsp" />
    </div>
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="../picture/department.png" class="d-block w-100">
          <div class="card-img-overlay text-white d-flex justify-content-center align-items-center flex-column">
            <p class="card-text h1 mb-3">Introduction</p>
            <p class="card-text h5">Welcome to the Student Management System website.This platform is designed to help students easily access</p>
            <p class="card-text h5">and manage their academic information. Through this site, you can view details about departments, teachers, and</p>
            <p class="card-text h5">courses offered by the university. You can also register for available classes, check your registration status, and explore</p>
            <p class="card-text h5">subjects that match your study plan. Our goal is to provide students with a simple, cconvenient, and efficient</p>
            <p class="card-text h5">experience in managing their learning process.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="../picture/department.png" class="d-block w-100">
          <div class="card-img-overlay text-white d-flex justify-content-center align-items-center flex-column">
            <p class="card-text h1 mb-3">The main function</p>
            <p class="card-text h5">View Department and class information</p>
            <p class="card-text h5">View teacher information</p>
            <p class="card-text h5">Can register for classes</p>
            <p class="card-text h5">View personal learning information</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div class="col-12 bg-white">
      <jsp:include page="Footer.jsp" />
    </div>
  </div>
</body>
</html>