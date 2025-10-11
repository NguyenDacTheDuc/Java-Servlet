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
      <p class="mb-0 h1 text-center text-white pt-4">Depatment List</p>
      <div class="col-12 p-5 d-flex flex-wrap justify-content-center align-items-center gap-1">
      <c:forEach items="${department}" var="d">
        <form action="class" method="post" class="col-3 m-3 p-0">
          <input type="hidden" name="department" value="${d.name}">
          <button type="submit"
            class="w-100 d-flex align-items-center justify-content-center rounded text-center text-light bg-danger border-0"
            style="height: 160px;"><h2 class="my-0">${d.name}</h2>
          </button>
        </form>
      </c:forEach>
      </div>
    </div>
    <div class="col-12 bg-white">
      <jsp:include page="Footer.jsp" />
    </div>
  </div>
</body>
</html>