<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Management Form</title>
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
<body>
  <div class="container-fluid p-0 row g-0">
    <div class="col-md-2 vh-auto d-inline">
      <jsp:include page="../Nav.jsp" />
    </div>
    <div class="col-md-10 vh-100 student d-flex justify-content-center align-items-center">
      <form action="form" method="post" class="px-5 py-4 bg-secondary rounded-5 w-auto">
        <p class="h1 text-center mb-3 text-white">Student Form</p>
        <div class="mb-4">
          <label class="form-label d-inline text-white" style="margin-right: 30px">Name</label>
          <input type="text" class="form-control d-inline w-auto me-5" name="name" value="${name}">
          <label class="form-label d-inline text-white" style="margin-right: 28px">Email</label>
          <input type="email" class="form-control d-inline w-auto" name="email" value="${email}">
        </div>
        <div class="mb-4">
          <label class="form-label d-inline text-white" style="margin-right: 28px">Phone</label>
          <input type="tel" class="form-control d-inline w-auto me-5" pattern="[0-9]{10}" name="phone" value="${phone}">
          <label class="form-label d-inline me-2 text-white">Birthday</label>
          <input type="date" class="form-control d-inline w-auto" name="birthday" value="${birthday}">
        </div>
        <div class="mb-4">
          <label class="form-label d-inline me-3 text-white">Address</label>
          <select id="conscious" class="form-select d-inline w-auto" name="address" style="margin-right: 80px">
            <option disabled selected>- Conscious/City -</option>
          </select>
          <label class="form-check-label d-inline text-white" style="margin-right: 24px">Sex</label>
          <c:choose>
          <c:when test="${not empty sex and sex eq 'Male'}">
            <input type="radio" value="Male" class="form-check-input me-1 mt-2 p-2" name="sex" checked><span class="text-white">Male</span>
            <input type="radio" value="Female" class="form-check-input me-1 ms-3 mt-2 p-2" name="sex" ><span class="text-white">Female</span>
          </c:when>
          <c:when test="${not empty sex and sex eq 'Female'}">
            <input type="radio" value="Male" class="form-check-input me-1 mt-2 p-2" name="sex"><span class="text-white">Male</span>
            <input type="radio" value="Female" class="form-check-input me-1 ms-3 mt-2 p-2" name="sex" checked><span class="text-white">Female</span>
          </c:when>
          <c:otherwise>
            <input type="radio" value="Male" class="form-check-input me-1 mt-2 p-2" name="sex"><span class="text-white">Male</span>
            <input type="radio" value="Female" class="form-check-input me-1 ms-3 mt-2 p-2" name="sex"><span class="text-white">Female</span>
          </c:otherwise>
          </c:choose>
        </div>
        <div class="mb-4">
          <label class="form-label d-inline text-white" style="margin-right: 37px">Class</label>
          <select id="selectClass" class="form-select d-inline w-auto" name="class" style="margin-right: 125px">
            <option disabled selected>- Class -</option>
            <c:forEach items="${listClass}" var="c">
              <option value="${c.id}"
                <c:if test="${not empty classId and c.id eq classId}">selected</c:if>>${c.name}
              </option>
            </c:forEach>
          </select>
          <label class="form-check-label d-inline me-2 text-white">Status</label>
          <c:choose>
          <c:when test="${not empty status and status eq 'Active'}">
            <input type="radio" value="Active" class="form-check-input me-1 mt-2 p-2" name="status" checked><span class="text-white">Active</span>
            <input type="radio" value="Inactive" class="form-check-input ms-2 me-1 mt-2 p-2" name="status"><span class="text-white">Inactive</span>
          </c:when>
          <c:when test="${not empty status and status eq 'Inactive'}">
            <input type="radio" value="Active" class="form-check-input me-1 mt-2 p-2" name="status"><span class="text-white">Active</span>
            <input type="radio" value="Inactive" class="form-check-input ms-2 me-1 mt-2 p-2" name="status" checked><span class="text-white">Inactive</span>
          </c:when>
          <c:otherwise>
            <input type="radio" value="Active" class="form-check-input me-1 mt-2 p-2" name="status" ><span class="text-white">Active</span>
            <input type="radio" value="Inactive" class="form-check-input ms-2 me-1 mt-2 p-2" name="status"><span class="text-white">Inactive</span>
          </c:otherwise>
          </c:choose>
          <c:if test="${not empty error}">
            <div class="alert text-warning">${error}</div>
          </c:if>
        </div>
        <div class="d-flex">
          <c:if test="${not empty id}">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="action" value="update">
          </c:if>
          <div class="text-center col-6"><a href="/admin/student" class="btn btn-warning fs-5 w-50"><i class="fas fa-arrow-left"></i> Back</a></div>
          <div class="text-center col-6"><button type="submit" class="btn btn-success btn-lg w-75"><i class="fas fa-check"></i> Save</button></div>
        </div>
      </form>
    </div>
  </div>
</body>
</html>

<script>
  const conscious = ["An Giang", "Bắc Ninh", "Cà Mau", "Cao Bằng", "Cần Thơ", "Đà Nẵng",
    "Đắk Lắk", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Nội", "Hà Tĩnh", "Hải Phòng","Huế", "Hưng Yên",
    "Khánh Hòa", "Lai Châu", "Lâm Đồng", "Lạng Sơn","Lào Cai", "Nghệ An", "Ninh Bình", "Phú Thọ", "Quảng Ngãi", "Quảng Ninh",
    "Quảng Trị", "Sơn La", "Tây Ninh", "Thái Nguyên","Thanh Hóa", "Hồ Chí Minh City", "Tuyên Quang", "Vĩnh Long	"];
  let selected = "${address}";
  conscious.forEach(v => {
    let option = document.createElement("option");
    option.value = v;
    option.text = v;
    if(v===selected)  option.selected=true;
    document.getElementById("conscious").appendChild(option);
  });
</script>