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
      <p class="mb-5 h1 text-center text-white pt-4">Student List in ${className}</p>
      <div class="d-flex justify-content-center align-items-center">
        <table class="table text-center rounded table-striped table-bordered table-hover">
        <tr class="fw-bold">
          <th>STT</th><th>Name</th><th>Birthday</th><th>Sex</th><th>Address</th>
        </tr>
        <c:forEach items="${studentList}" var="s">
          <tr class="align-middle">
            <th><%=i++%></th><th>${s.name}</th><th>${s.birthday}</th><th>${s.sex}</th><th>${s.address}</th>
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