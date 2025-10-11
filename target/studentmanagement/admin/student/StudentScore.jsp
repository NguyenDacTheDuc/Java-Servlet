<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Score</title>
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
      <p class="h1 text-center mt-5 mb-4 pt-2 pb-1">Subject score of student with id=${sessionScope.id}</p>
      <div style="height: 40px">
        <button onclick="location.href='student'" class="btn btn-warning" style="margin-left: 25%"><i class="fas fa-arrow-left"></i> Back</button>
        <form action="/admin/grade" method="post" class="d-inline-block w-50 mx-5">
          <input type="hidden" name="findBySubject" value="true">
          <input type="hidden" name="idst" value="${id}">
          <input type="text" class="form-control w-25 d-inline-block pt-1" name="findName" placeholder="Search name...">
          <button class="btn btn-primary"><i class="fas fa-search"></i> Search</button>
        </form>
      </div>
      <div class="d-flex justify-content-center align-items-center mt-4">
      <table class="table text-center rounded table-striped table-bordered table-hover w-50">
        <tr class="fw-bold">
          <th>STT</th><th class="w-auto">Subject Name</th><th class="w-25">Score</th><th>Function</th>
        </tr>
        <c:forEach items="${listSubjectScore}" var="s">
          <tr class="align-middle">
            <th><%=i++%></th><th>${s.subjectName}</th>
            <th><input id="score${s.subjectId}" type="text" value="${s.grade}" class="w-25" disabled></th>
            <th>
            <button onclick="edit(${s.subjectId})" class="btn btn-warning"><i class="fas fa-edit"></i> Edit</button>
            <button onclick="save(${s.studentId}, ${s.subjectId})" class="btn btn-success" disabled id="btnSuccess${s.subjectId}"><i class="fas fa-save"></i> Save</button>
            </th>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</body>
</html>

<script>
  let edit=(id)=>{
    document.getElementById("score"+id).removeAttribute("disabled");
    document.getElementById("btnSuccess"+id).removeAttribute("disabled");
  }
  let save = async (idst, idsu) => {
    let grade = document.getElementById('score'+idsu).value;
    await fetch('/admin/grade', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: 'studentId=' + idst + '&subjectId=' + idsu + '&grade=' + grade
    });
    location.reload();
  }
</script>