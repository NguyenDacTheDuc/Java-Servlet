<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  .anav:hover{
    background: linear-gradient(to right, blue, red);
  }
  .logout:hover{
    box-shadow: 0 0 10px rgba(255, 0, 0, 1);
  }
</style>
<div class="container-fluid p-0">
  <div class="bg-dark text-light vh-100 d-flex flex-column">
    <div class="text-center pt-4 mb-5" >
      <i class="fas fa-user-cog fa-6x mb-1"></i>
      <h3>Admin: ${sessionScope.user.username}</h3>
      <a href="/admin/change" class="d-block text-light text-decoration-none p-2 bg-secondary anav">Change password</a>
    </div>
    <h5 >Menu Admin</h5>
    <ul class="nav d-flex flex-column">
      <li class="nav-item">
        <a class="nav-link text-center text-white bg-secondary anav" href="/admin/user">User</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-center text-white bg-secondary anav" href="/admin/student">Student</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-center text-white bg-secondary anav" href="/admin/teacher">Teacher</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-center text-white bg-secondary anav" href="/admin/department">Department</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-center text-white bg-secondary anav" href="/admin/class">Class</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-center text-white bg-secondary anav" href="/admin/subject">Subject</a>
      </li>
      <c:if test="${sessionScope.user.role eq 'master'}">
        <li class="nav-item">
          <a class="nav-link text-center text-white bg-secondary anav" href="/admin/browse">Browse</a>
        </li>
      </c:if>
    </ul>
    <button onclick="window.location.href='${pageContext.request.contextPath}/logout'" class="logout btn btn-danger rounded-pill d-block w-100 mt-auto mb-3">Log out</button>
  </div>
</div>
