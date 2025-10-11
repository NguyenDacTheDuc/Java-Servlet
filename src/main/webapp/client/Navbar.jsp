<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<style>

</style>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
</head>
<body>
  <div class="container-fluid home">
    <ul class="nav">
      <li class="nav-item h1"><a class="nav-link" aria-current="page" href="home">EPUser</a></li>
      <li class="la nav-item px-5 mt-2 h3 d-flex align-items-center"><a class="nav-link" href="department">Department</a></li>
      <li class="la nav-item px-5 mt-2 h3 d-flex align-items-center"><a class="nav-link" href="teacher">Teacher</a></li>
      <li class="la nav-item px-5 mt-2 h3 d-flex align-items-center">
        <a class="nav-link ${registered eq 0 ? 'disabled' : ''}" href="score?id=${registered}">Subject Score</a>
      </li>
      <li class="la nav-item px-5 mt-2 h3 d-flex align-items-center">
        <a class="nav-link ${registered eq 0 ? 'disabled' : ''}" href="about">About</a>
      </li>
      <li class="nav-item dropdown ms-auto h1 d-flex align-items-center">
        <a class="nav-link " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          <i class="fa-regular fa-circle-user"></i>
        </a>
        <ul class="dropdown-menu dropdown-menu-end text-center">
          <li><a href="change" class="change dropdown-item rounded">Change password</a></li>
          <li><a class="out dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt me-3"></i>Log out</a></li>
        </ul>
      </li>
    </ul>
  </div>
</body>
</html>