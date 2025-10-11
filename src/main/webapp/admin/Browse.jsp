<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Browse</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
  <style>
    .browse{
      background: url("../picture/7.jpg");
      background-size: cover;
    }
  </style>
</head>
<% int i=1; %>
<body>
  <div class="container-fluid p-0 row g-0">
    <div class="col-md-2 vh-auto d-inline">
      <jsp:include page="Nav.jsp" />
    </div>
    <div class="col-md-10 vh-100 browse">
      <p class="h1 text-center mt-5 mb-4 pt-2 pb-1 text-white">Student Browse Page</p>
      <div class="d-flex justify-content-center align-items-center mt-4">
      <table class="table text-center rounded table-striped table-bordered table-hover w-75">
        <tr class="fw-bold">
          <th>STT</th><th>UserID</th><th>Name</th><th>Birthday</th><th>Phone</th><th>Sex</th><th>Address</th><th>ClassID</th><th>Function</th>
        </tr>
        <c:forEach items="${registerClassList}" var="rc">
          <tr class="align-middle">
            <th><%=i++%></th><th>${rc.userId}</th><th>${rc.name}</th><th>${rc.birthday}</th><th>${rc.phone}</th><th>${rc.sex}</th><th>${rc.address}</th><th>${rc.classId}</th>
            <th>
            <button onclick="accept(${rc.userId}, '${rc.name}', '${rc.birthday}', '${rc.phone}', '${rc.sex}', '${rc.address}', ${rc.classId})" class="btn btn-success"><i class="fas fa-check"></i> Accept</button>
            <button onclick="del(${rc.userId})" class="btn btn-danger"><i class="fas fa-remove"></i> Remove</button>
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
  let accept=async(userId, name, birthday, phone, sex, address, classId)=>{
    await fetch('browse', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: 'userId='+encodeURIComponent(userId)+
      '&name='+encodeURIComponent(name)+
      '&birthday='+encodeURIComponent(birthday)+
      '&phone='+encodeURIComponent(phone)+
      '&sex='+encodeURIComponent(sex)+
      '&address='+encodeURIComponent(address)+
      '&classId='+encodeURIComponent(classId)
    })
    location.reload();
  }

  let del=async(id)=>{
    if(confirm("Are you sure to remove this user?")){
      await fetch('browse?id='+id, {method: 'DELETE'});
      location.reload();
    }
  }
</script>