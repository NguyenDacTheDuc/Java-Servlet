<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <link rel="stylesheet" href="/css/Login.css" >
</head>
<body>
  <div class="container-fluid d-flex justify-content-center align-items-center vh-100">
    <div class="px-5 pt-5 pb-4 rounded bong text-light col-md-4">
      <div class="text-center"><i class="fas fa-user-circle fa-7x"></i></div>
      <h1 class="text-center pb-3">User Login</h1>
      <form action="login" method="post" autocomplete="off">
        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text"><i class="fas fa-user"></i></span>
          <div class="form-floating">
            <input id="usern" type="text" class="form-control" id="username" name="username" placeholder="" required>
            <label for="username" class="form-label text-dark">Username</label>
          </div>
        </div>
        <div class="input-group input-group-lg">
          <span class="input-group-text"><i class="fas fa-lock"></i></span>
          <div class="form-floating">
            <input id="passw" type="password" class="form-control" id="password" name="password" placeholder="" required>
            <label for="password" class="form-label text-dark">Password</label>
          </div>
        </div>
        <c:if test="${not empty error}">
          <div class="text-danger fw-bold mb-1">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
          <div class="text-success fw-bold mb-1">${success}</div>
        </c:if>
        <div class="my-3">
          <button type="submit" class="btn btn-primary btn-lg w-100 rounded">Sign in</button>
        </div>
        <div class="hr d-flex">
          <hr> <span class="text-center text-white">Not a member</span> <hr>
        </div>
        <div class="my-2">
          <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary btn-lg w-100 rounded">Register</a>
        </div>
        <div class="mb-3 d-flex justify-content-center">
          <a href="${pageContext.request.contextPath}/forget" class="text-decoration-none text-light"> Forgot Password ?</a>
        </div>
      </form>
    </div>
  </div>
</body>

<script>
  window.onload = () => {
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
  };
</script>
