<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Management</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <style>
    .student{
      background: url("../../picture/6.png");
      background-size: cover;
    }
  </style>
</head>
<% int i=1; %>
<body>
  <div class="container-fluid p-0 row g-0">
    <div class="col-md-2 vh-auto d-inline">
      <jsp:include page="../Nav.jsp" />
    </div>
    <div class="col-md-10 vh-100 student">
      <p class="h1 text-center mt-5 mb-4 pt-2 pb-1">Student Management Page</p>
      <div style="height: 40px">
        <button onclick="location.href='student/form'" class="btn btn-success mx-5"><i class="fas fa-plus"></i> Add</button>
        <form action="student" method="post" class="d-inline-block w-50">
          <input type="text" class="form-control w-50 d-inline-block pt-1" name="findName" placeholder="Search name...">
          <button class="btn btn-primary"><i class="fas fa-search"></i> Search</button>
        </form>
      </div>
      <div class="d-flex justify-content-center align-items-center mt-4">
      <table class="table text-center rounded table-striped table-bordered table-hover w-100">
        <tr class="fw-bold">
          <th>STT</th><th>ID</th><th>Name</th><th>Email</th><th>Birthday</th><th>Sex</th><th>Phone</th><th>Address</th><th>Class</th><th>Status</th><th>Function</th>
        </tr>
        <c:forEach items="${listStudent}" var="s">
          <tr class="align-middle">
            <th><%=i++%></th><th>${s.id}</th><th>${s.name}</th><th>${s.email}</th><th>${s.birthday}</th><th>${s.sex}</th><th>${s.phone}</th><th>${s.address}</th><th>${s.className}</th><th>${s.status}</th>
            <th>
            <button onclick="location.href='grade?idst=${s.id}'" class="btn btn-info"
            <c:if test="${s.status eq 'Inactive'}">disabled</c:if>><i class="fas fa-star"></i> Score</button>
            <button onclick="location.href='student/form?id=${s.id}'" class="btn btn-warning"><i class="fas fa-edit"></i> Edit</button>
            <button onclick="del(${s.id})" class="btn btn-danger"><i class="fas fa-trash-can"></i> Remove</button>
            </th>
            </th>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</body>
</html>

<script>
  let del=async(id)=>{
    if(confirm("Are you sure to remove this student?")){
      await fetch('student?id='+id, {method: 'DELETE'});
      location.reload();
    }
  }
</script>