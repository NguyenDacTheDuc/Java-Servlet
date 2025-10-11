<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Home</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <link rel="stylesheet" href="../css/AHome.css">
</head>

<body>
  <div class="container-fluid p-0 vh-100">
    <div class="py-5">
      <h1 class="text-center text-light">Welcome to the admin page</h1>
      <div class="dropdown position-absolute m-4 top-0 end-0">
        <button class="btn text-light dropdown-toggle btn-outline-primary" data-bs-toggle="dropdown">Hello, ${sessionScope.user.username}</button>
        <ul class="dropdown-menu py-0">
          <li><a href="change" class="change dropdown-item rounded">Change password</a></li>
          <li><a href="${pageContext.request.contextPath}/logout" class="out dropdown-item rounded"><i class="fas fa-sign-out-alt me-3"></i>Log out</a></li>
        </ul>
      </div>
    </div>
    <div class="d-flex flex-wrap justify-content-center align-items-center">
      <a href="user" class="user text-decoration-none mx-4 my-3 p-5 d-block rounded text-center text-light">
        <h2 class="my-5">User</h2>
      </a>
      <a href="student" class="student text-decoration-none mx-4 my-3 p-5 d-block bg-secondary rounded text-center text-light">
        <h2 class="my-5">Student</h2>
      </a>
      <a href="teacher" class="teacher text-decoration-none mx-4 my-3 p-5 d-block bg-primary rounded text-center text-light">
        <h2 class="my-5">Teacher</h2>
      </a>
      <a href="department" class="department text-decoration-none mx-4 my-3 p-5 d-block bg-danger rounded text-center text-light">
        <h2 class="my-5">Department</h2>
      </a>
      <a href="class" class="class text-decoration-none mx-4 my-3 p-5 d-block bg-success rounded text-center text-light">
        <h2 class="my-5">Class</h2>
      </a>
      <a href="subject" class="subject text-decoration-none mx-4 my-3 p-5 d-block bg-success rounded text-center text-light">
        <h2 class="my-5">Subject</h2>
      </a>
    </div>
  </div>
</body>
</html>