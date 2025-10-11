<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <link rel="stylesheet" href="/css/Login.css">
</head>
<body class="container-fluid d-flex justify-content-center align-items-center vh-100">
  <div class="px-5 pt-5 pb-4 rounded bong text-white col-md-4">
    <h1 class="text-center mb-4">Register</h1>
    <form action="register" method="post">
      <div class="input-group input-group-lg mb-3">
        <span class="input-group-text"><i class="fas fa-user"></i></span>
        <div class="form-floating">
          <input class="form-control" type="text" name="username" placeholder="" required>
          <label class="form-label text-dark">Username</label>
        </div>
      </div>
      <div class="input-group input-group-lg mb-3">
        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
        <div class="form-floating">
          <input type="email" class="form-control" name="email" required placeholder="">
          <label class="form-label text-dark" >Email</label>
        </div>
      </div>
      <div class="input-group mb-3 input-group-lg">
        <span class="input-group-text"><i class="fas fa-key"></i></span>
        <div class="form-floating">
          <input type="password" class="form-control" name="password" placeholder="" required>
          <label class="form-label text-dark">Password</label>
        </div>
      </div>
      <div class="input-group input-group-lg mt-3">
        <span class="input-group-text"><i class="fas fa-lock"></i></span>
        <div class="form-floating">
          <input type="password" class="form-control" name="enPassword" required placeholder="">
          <label class="form-label text-dark" >En-enter password</label>
        </div>
      </div>
      <c:if test="${not empty requestScope.error}">
        <div class="text-danger fw-bold mb-1">${requestScope.error}</div>
      </c:if>
      <div class="my-4">
        <button type="submit" class="btn btn-primary btn-lg w-100 rounded">Sing up</button>
      </div>
      <div class="mb-3 d-flex justify-content-center">
        <a href="Login.jsp" class="text-decoration-none text-light"> &larr; Already have an account? Login</a>
      </div>
    </form>
  </div>
</body>
</html>