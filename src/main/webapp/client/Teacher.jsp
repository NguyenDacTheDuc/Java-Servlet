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
<% int i=1; %>
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
    <div class="col-12 bg-secondary">
      <p class="mb-5 h1 text-center text-white pt-4">Teacher List</p>
      <div class="d-flex justify-content-center align-items-center">
        <table class="table text-center w-75 rounded table-striped table-bordered table-hover">
          <tr class="fw-bold ">
            <th>STT</th><th>Teacher Name</th><th>Lead Teacher</th><th>Function</th>
          </tr>
          <c:forEach items="${teacherList}" var="teacher">
          <tr class="align-middle">
            <th><%=i++%></th><th>${teacher.name}</th><th>${teacher.email}</th>
            <th>
            <button onclick="location.href='student?idtc=${teacher.id}'" class="btn btn-success"><i class="fas fa-eye"></i> Show Student</button>
            <button onclick="location.href='registerclass?idtc=${teacher.id}'" class="btn btn-warning ${registered ne 0 ? 'disabled' : ''}"><i class="fas fa-user-plus"></i> Register</button>
            </th>
          </tr>
        </c:forEach>
        </table>
      </div>
    </div>
    <div class="col-12 bg-white">
      <jsp:include page="Footer.jsp" />
    </div>
  </div>
</body>
</html>