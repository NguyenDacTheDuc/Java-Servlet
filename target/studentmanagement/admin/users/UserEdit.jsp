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
    <form action="update?id=${requestScope.user.id}" method="post" class="p-5 bg-secondary rounded">
      <label class="form-label">Username</label>
      <c:choose>
        <c:when test="${not empty requestScope.name}">
          <input type="text" class="form-control mb-2" name="username" value="${requestScope.name}">
        </c:when>
        <c:otherwise>
          <input type="text" class="form-control mb-2" name="username" value="${requestScope.user.username}">
        </c:otherwise>
      </c:choose>
      <c:if test="${not empty requestScope.errorN}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorN}</div>
      </c:if>
      <label  class="form-label">Email</label>
      <c:choose>
        <c:when test="${not empty requestScope.email}">
          <input type="email" class="form-control mb-2" name="email" value="${requestScope.email}">
        </c:when>
        <c:otherwise>
          <input type="email" class="form-control mb-2" name="email" value="${requestScope.user.email}">
        </c:otherwise>
      </c:choose>
      <c:if test="${not empty requestScope.errorE}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorE}</div>
      </c:if>
      <label class="form-label">Password</label>
      <c:choose>
        <c:when test="${not empty requestScope.password}">
          <input type="password" class="form-control mb-2" name="password" value="${requestScope.password}">
        </c:when>
        <c:otherwise>
          <input type="password" class="form-control mb-2" name="password" value="${requestScope.user.password}">
        </c:otherwise>
      </c:choose>
      <c:if test="${not empty requestScope.errorP}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorP}</div>
      </c:if>
      <c:if test="${not empty requestScope.errorPa}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorPa}</div>
      </c:if>
      <label class="form-label">En-enter Password</label>
      <c:choose>
        <c:when test="${not empty requestScope.enPassword}">
          <input type="password" class="form-control" name="enPassword" value="${requestScope.enPassword}">
        </c:when>
        <c:otherwise>
          <input type="password" class="form-control" name="enPassword" value="${requestScope.user.password}">
        </c:otherwise>
      </c:choose>
      <c:if test="${not empty requestScope.errorEn}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorEn}</div>
      </c:if>
      <c:if test="${not empty requestScope.errorEnP}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorEnP}</div>
      </c:if>
      <div class="mt-4">
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
      </div>
      <c:if test="${not empty requestScope.errorR}">
        <div class="text-danger fw-bold mb-1">${requestScope.errorR}</div>
      </c:if>
      <c:if test="${not empty requestScope.error}">
        <div class="text-danger fw-bold mb-1">${requestScope.error}</div>
      </c:if>
      <div class="d-flex mt-4">
        <button type="submit" class="btn btn-success btn-lg w-75 m-auto d-block me-2">Save</button>
        <a href="/admin/user" class="btn btn-warning align-content-center fs-5 px-3">Back</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>