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
<% int i=1; %>
<body>
  <div class="container-fluid p-0 row g-0">
    <div class="col-md-2 d-inline vh-auto">
      <jsp:include page="../Nav.jsp" />
    </div>
      <div class="col-md-10 user vh-100">
        <p class="h1 text-center mt-5 mb-4 pt-2 pb-1">User Management Page</p>
        <button onclick="window.location.href='users/add'" class="btn btn-success my-2" style="margin-left: 12.5%"><i class="fas fa-plus"></i> Add</button>
        <div class="d-flex justify-content-center align-items-center">
        <table class="table text-center w-75 rounded table-striped table-bordered table-hover">
          <tr class="fw-bold">
            <th>STT</th><th>Username</th><th>Password</th><th>Email</th><th>Role</th><th>Function</th>
          </tr>
          <c:forEach items="${requestScope.listUser}" var="user">
            <tr class="align-middle">
              <th><%=i++%></th><th>${user.username}</th><th>${user.password}</th><th>${user.email}</th><th>${user.role}</th>
              <th>
                <button onclick="window.location.href='users/update?id=${user.id}'" class="btn btn-warning"><i class="fas fa-edit"></i> Edit</button>
                <button onclick="window.location.href='users/remove?id=${user.id}'" class="btn btn-danger"><i class="fas fa-trash-can"></i> Remove</button>
              </th>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</body>
</html>
