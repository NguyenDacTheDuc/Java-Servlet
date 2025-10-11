<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teacher Management</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <style>
    .teacher{
      background: url("../../picture/4.jpeg");
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
    <div class="col-md-10 vh-100 teacher">
      <p class="h1 text-center mt-5 mb-4 pt-2 pb-1">Teacher Management Page</p>
      <div style="height: 40px">
        <button onclick="add()" class="btn btn-success" style="margin-left: 12.5%"><i class="fas fa-plus"></i> Add</button>
        <form action="teacher" method="post" class="d-inline-block w-50">
          <input id="myInputName" type="text" class="form-control w-25 d-inline-block pt-1" name="name" placeholder="Name Teacher" style="margin-left:2%" disabled>
          <input id="myInputEmail" type="email" class="form-control w-auto d-inline-block pt-1" name="email" placeholder="Email" style="margin-left:1%" disabled>
          <select id="myInputStatus" class="form-select w-auto d-inline-block pt-1" name="status" style="margin-left:1%" disabled>
            <option value="" disabled selected>- Status -</option>
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
          <button id="myButton" class="btn btn-success" disabled><i class="fas fa-check"></i> Save</button>
        </form>
        <form action="teacher" method="post" class="d-inline-block" style="margin-left:-1%">
          <input type="text" class="form-control w-50 d-inline-block pt-1" name="findName" placeholder="Search...">
          <button class="btn btn-primary"><i class="fas fa-search"></i> Search</button>
        </form>
      </div>
      <c:if test="${not empty error}">
        <div class="fw-bold text-danger d-inline-block" style="margin-left: 19%">${error}</div>
      </c:if>
      <div class="d-flex justify-content-center align-items-center mt-4">
      <table class="table text-center w-75 rounded table-striped table-bordered table-hover">
        <tr class="fw-bold">
          <th>STT</th><th>Teacher Name</th><th>Email</th><th>Status<th>Function</th>
        </tr>
        <c:forEach items="${listTeacher}" var="teacher">
          <tr class="align-middle">
            <th><%=i++%></th><th>${teacher.name}</th><th>${teacher.email}</th><th>${teacher.status}</th>
            <th>
            <button onclick="edit(${teacher.id}, '${teacher.name}', '${teacher.email}', '${teacher.status}')" class="btn btn-warning"><i class="fas fa-edit"></i> Edit</button>
            <button onclick="del(${teacher.id})" class="btn btn-danger"><i class="fas fa-trash-can"></i> Remove</button>
            </th>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</body>
</html>

<script>
  let add=()=>{
    document.getElementById("myInputName").value = "";
    document.getElementById("myInputEmail").value = "";
    document.getElementById("myInputStatus").setAttribute("disabled", "true");
    document.getElementById("myButton").onclick=null;
    document.getElementById("myInputName").removeAttribute("disabled");
    document.getElementById("myInputEmail").removeAttribute("disabled");
    document.getElementById("myButton").removeAttribute("disabled");
  }

  let edit=(id, name, email, status)=>{
    add();
    document.getElementById("myInputStatus").removeAttribute("disabled");
    document.getElementById("myInputName").value = name;
    document.getElementById("myInputEmail").value = email;
    document.getElementById("myInputStatus").value = status;
    document.getElementById("myButton").onclick=(event)=>{
      event.preventDefault();
      bEdit(id);
    };
  }

  let bEdit=async(id)=>{
    let n=document.getElementById("myInputName").value;
    let e=document.getElementById("myInputEmail").value;
    let s=document.getElementById("myInputStatus").value;
    await fetch('teacher', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: 'action=update'+
      '&id='+encodeURIComponent(id)+
      '&name='+encodeURIComponent(n)+
      '&email='+encodeURIComponent(e)+
      '&status='+encodeURIComponent(s)
    });
    location.reload();
  }

  let del= async (id)=>{
    if(confirm("Are you sure to delete this teacher?")) {
      await fetch('teacher?id=' + id, { method: 'DELETE'});
      location.reload();
    }
  }
</script>