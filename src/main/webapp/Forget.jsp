<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Forget</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <link rel="stylesheet" href="/css/Login.css" >
</head>
<body>
  <div class="container-fluid d-flex justify-content-center align-items-center vh-100">
    <div class="px-5 pt-5 pb-4 rounded bong text-light col-md-4">
      <div class="text-center"><i class="fas fa-user-circle fa-7x"></i></div>
      <h1 class="text-center pb-3">Forget Password</h1>
      <form action="forget" method="post">
        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text"><i class="fas fa-user"></i></span>
          <div class="form-floating">
            <input type="text" class="form-control" name="username" placeholder="" required>
            <label for="username" class="form-label text-dark">Username</label>
          </div>
        </div>
        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text"><i class="fas fa-envelope"></i></span>
          <div class="form-floating">
            <input type="email" class="form-control" name="email" placeholder="" required>
            <label for="email" class="form-label text-dark">Email</label>
          </div>
        </div>
        <div class="mb-3">
          <button type="submit" class="btn btn-primary btn-lg w-100 rounded">Reset</button>
        </div>
        <div class="mb-3 d-flex justify-content-center">
          <a href="login" class="text-decoration-none text-light"> &larr; Already have an account? Login</a>
        </div>
      </form>
    </div>
  </div>
</body>