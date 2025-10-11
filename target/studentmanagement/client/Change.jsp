<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Change Password</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
</head>
<body>
  <div class="container-fluid vh-100 d-flex justify-content-center align-items-center" style="background: url('../picture/lagque.jpg'); background-size: cover">
    <div class="px-5 pt-5 pb-4 rounded text-light col-md-4" style="box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);" >
      <div class="text-center"><i class="fas fa-user-shield fa-7x"></i></div>
      <h1 class="text-center pb-3">Change Password</h1>
      <form action="change" method="post">
        <div class="input-group input-group-lg mb-3">
          <span class="input-group-text"><i class="fas fa-key"></i></span>
          <div class="form-floating">
            <input type="password" class="form-control" name="oldPassword" placeholder="" required>
            <label class="form-label text-dark">Old Password</label>
          </div>
        </div>
        <div class="input-group input-group-lg mb-3">
          <span class="input-group-text"><i class="fas fa-lock"></i></span>
          <div class="form-floating">
            <input type="password" class="form-control" name="newPassword" placeholder="" required>
            <label class="form-label text-dark">New Password</label>
          </div>
        </div>
        <div class="input-group input-group-lg mb-3">
          <span class="input-group-text"><i class="fas fa-lock"></i></span>
          <div class="form-floating">
            <input type="password" class="form-control" name="enPassword" placeholder="" required>
            <label class="form-label text-dark">En-enter Password</label>
          </div>
        </div>
        <div class="mb-2 d-flex">
          <button type="submit" class="btn btn-primary btn-lg w-50 rounded me-1">Change</button>
          <a href="admin/home" class="btn btn-secondary w-50 ms-1 d-flex align-items-center justify-content-center fs-5">Home</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>