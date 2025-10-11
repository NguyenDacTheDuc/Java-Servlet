<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Class Management</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <style>
    .class{
      background: url("../../picture/5.jpg");
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
    <div class="col-md-10 vh-100 class">
      <p class="h1 text-center mt-5 mb-4 pt-2 pb-1">Class Management Page</p>
      <div style="height: 40px">
        <button onclick="add()" class="btn btn-success" style="margin-left: 12.5%"><i class="fas fa-plus"></i> Add</button>
        <form action="class" method="post" class="d-inline-block w-50">
          <input id="inputName" type="text" class="form-control w-25 d-inline-block pt-1" name="name" placeholder="Name Class" style="margin-left:2%" disabled>
          <select id="selectTeacher" class="form-select w-auto d-inline-block pt-1 ms-2" name="idTeacher" disabled>
            <option value="" disabled selected>- Teacher -</option>
            <c:forEach items="${listTeacher}" var="teacher">
              <option value="${teacher.id}">${teacher.name}</option>
            </c:forEach>
          </select>
          <select id="selectDepartment" class="form-select w-auto d-inline-block pt-1 mx-2" name="idDepartment" disabled>
            <<option value="" disabled selected>- Department -</option>
            <c:forEach items="${listDepartment}" var="department">
              <option value="${department.id}">${department.name}</option>
            </c:forEach>
          </select>
          <button id="button" class="btn btn-success" disabled><i class="fas fa-check"></i> Save</button>
        </form>
        <form action="class" method="post" class="d-inline-block" style="margin-left:-1%">
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
          <th>STT</th><th>Class Name</th><th>Lead Teacher</th><th>Department</th><th>Function</th>
        </tr>
        <c:forEach items="${listClass}" var="clas">
          <tr class="align-middle">
            <th><%=i++%></th><th>${clas.name}</th><th>${clas.teacherName}</th><th>${clas.departmentName}</th>
            <th>
            <button onclick="edit(${clas.id}, '${clas.name}', '${clas.teacherId}', '${clas.departmentId}')" class="btn btn-warning"><i class="fas fa-edit"></i> Edit</button>
            <button onclick="del(${clas.id})" class="btn btn-danger"><i class="fas fa-trash-can"></i> Remove</button>
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
    document.getElementById("inputName").value = "";
    document.getElementById("selectTeacher").value="";
    document.getElementById("selectDepartment").value="";
    document.getElementById("button").onclick=null;
    document.getElementById("inputName").removeAttribute("disabled");
    document.getElementById("selectTeacher").removeAttribute("disabled");
    document.getElementById("selectDepartment").removeAttribute("disabled");
    document.getElementById("button").removeAttribute("disabled");
  }

  let edit=(id, name, teacher, department)=>{
    add();
    document.getElementById("inputName").value = name;
    document.getElementById("selectTeacher").value=teacher;
    document.getElementById("selectDepartment").value = department;
    document.getElementById("button").onclick=(event)=>{
      event.preventDefault();
      bEdit(id);
    };
  }

  let bEdit=async(id)=>{
    let n=document.getElementById("inputName").value;
    let t=document.getElementById("selectTeacher").value;
    let d=document.getElementById("selectDepartment").value;
    await fetch('class', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: 'action=update'+
      '&id='+encodeURIComponent(id)+
      '&name='+encodeURIComponent(n)+
      '&idTeacher='+encodeURIComponent(t)+
      '&idDepartment='+encodeURIComponent(d)
    });
    location.reload();
  }

  let del= async (id)=>{
    if(confirm("Are you sure to delete this class?")) {
      await fetch('class?id='+id, { method: 'DELETE'});
      location.reload();
    }
  }
</script>