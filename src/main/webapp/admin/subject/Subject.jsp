<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Subject Management</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <style>
    .subject{
      background: url("../../picture/3.jpeg");
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
    <div class="col-md-10 vh-100 subject">
      <p class="h1 text-center mt-5 mb-4 pt-2 pb-1">Subject Management Page</p>
      <div style="height: 40px">
        <button onclick="add()" class="btn btn-success" style="margin-left: 12.5%"><i class="fas fa-plus"></i> Add</button>
        <form id="formId" action="subject" method="post" class="d-inline-block w-25">
          <input id="myInput" type="text" class="form-control w-auto d-inline-block pt-1" name="name" placeholder="Name Subject" style="margin-left:2%" disabled>
          <button id="myButton" class="btn btn-success" disabled><i class="fas fa-check"></i> Save</button>
        </form>
        <form action="subject" method="post" class="d-inline-block" style="margin-left:20%">
          <input type="text" class="form-control w-auto d-inline-block pt-1" name="findName" placeholder="Search...">
          <button class="btn btn-primary"><i class="fas fa-search"></i> Search</button>
        </form>
      </div>
      <c:if test="${not empty error}">
        <div class="fw-bold text-danger d-inline-block" style="margin-left: 19%">${error}</div>
      </c:if>
      <div class="d-flex justify-content-center align-items-center mt-4">
      <table class="table text-center w-75 rounded table-striped table-bordered table-hover">
        <tr class="fw-bold">
          <th>STT</th><th>Subject Name</th><th>Function</th>
        </tr>
        <c:forEach items="${listSubject}" var="subject">
          <tr class="align-middle">
            <th><%=i++%></th><th>${subject.name}</th>
            <th>
            <button onclick="location.href='grade?idsu=${subject.id}&name=${subject.name}'" class="btn btn-info"><i class="fas fa-star"></i> Score</button>
            <button onclick="edit(${subject.id}, '${subject.name}')" class="btn btn-warning"><i class="fas fa-edit"></i> Edit</button>
            <button onclick="del(${subject.id})" class="btn btn-danger"><i class="fas fa-trash-can"></i> Remove</button>
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
    document.getElementById("myInput").value="";
    document.getElementById("myButton").onclick=null;
    document.getElementById("myInput").removeAttribute("disabled");
    document.getElementById("myButton").removeAttribute("disabled");
  }

  let edit=(id, name)=>{
    add();
    document.getElementById("myInput").value=name;
    document.getElementById("myButton").onclick=(event)=>{
      event.preventDefault();
      bEdit(id);
    }
  }

  let bEdit=async(id)=>{
    n=document.getElementById("myInput").value;
    await fetch('subject', {
      method: 'POST',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      body: 'action=update&id='+encodeURIComponent(id)+'&name='+encodeURIComponent(n)
    });
    location.reload();
  }

  let del=async(id)=>{
    if(confirm("Are you sure to remove this subject?")){
      await fetch('subject?id='+id, {method: 'DELETE'});
      location.reload();
    }
  }
</script>
