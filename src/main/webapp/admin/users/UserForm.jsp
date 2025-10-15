<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Management</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <style>
      .user{
          background: url("../../picture/1.jpeg");
          background-size: cover;
      }
  </style>
</head>
<body>
  <div class="container-fluid p-0 row g-0">
    <div class="col-md-2 d-inline vh-auto">
      <jsp:include page="../Nav.jsp" />
    </div>
    <div class="col-md-10 user d-flex justify-content-center align-items-center">
      <form action="form" method="post" class="p-5 bg-secondary rounded-5 text-white">
        <label class="form-label">Username</label>
        <input type="text" class="form-control mb-2" name="username" value="${name}">
        <label  class="form-label">Email</label>
        <input type="email" class="form-control mb-2" name="email" value="${email}">
        <label class="form-label">Password</label>
        <input type="password" class="form-control mb-2" name="password" value="${password}">
        <label class="form-label">En-enter Password</label>
        <input type="password" class="form-control mb-3" name="enPassword" value="${enPassword}">
        <label class="form-check-label me-3">Role</label>
        <c:choose>
          <c:when test="${sessionScope.user.role eq 'master'}">
            <input type="radio" value="admin" class="form-check-input me-1" name="role">Admin
          </c:when>
          <c:otherwise>
            <input type="radio" value="admin" class="form-check-input me-1" name="role" disabled>Admin
          </c:otherwise>
        </c:choose>
        <input type="radio" value="viewer" class="form-check-input ms-2 me-1" name="role">User
        <c:if test="${not empty requestScope.error}">
          <div class="text-danger fw-bold mb-1 mt-2">${requestScope.error}</div>
        </c:if>
        <div class="d-flex mt-2">
        <c:if test="${not empty id}">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="action" value="update">
          </c:if>
          <button type="submit" class="btn btn-success btn-lg w-75 m-auto d-block me-2">Save</button>
          <a href="/admin/user" class="btn btn-warning align-content-center fs-5 px-3">Back</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
