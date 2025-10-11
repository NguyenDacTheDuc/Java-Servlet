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
      <p class="mb-0 h1 text-center text-white pt-4">Class Register</p>
      <div class="col-12 d-flex justify-content-center align-items-center py-5">
        <form action="registerclass" method="post" class="d-flex flex-column bg-danger px-5 pt-4 pb-3 rounded-5 text-white mx-auto">
          <p class="text-center h3 text-white">Enter Information</p>
          <input type="hidden" name="userId" value="${sessionScope.user.id}">
          <input type="hidden" name="classId" value="${classId}">
          <div class="row mb-3">
            <div class="col-6">
              <label class="form-label">Name</label>
              <input type="text" class="form-control" name="name" required>
            </div>
            <div class="col-6">
              <label class="form-label">Birthday</label>
              <input type="date" class="form-control" name="birthday" required>
            </div>
          </div>
          <div class="row mb-4">
            <div class="col-6">
              <label class="form-label">Phone</label>
              <input type="text" class="form-control" pattern="[0-9]{10}" name="phone" required>
            </div>
            <div class="col-6">
              <label class="form-label">Address</label>
              <select id="conscious" class="form-select d-inline" name="address" required>
                <option value="" disabled selected>- Conscious/City -</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col-6 mb-3">
              <label class="form-check-label me-3">Sex</label>
              <input type="radio" value="Male" class="form-check-input me-1" name="sex"><span class="me-2" required>Male</span>
              <input type="radio" value="Female" class="form-check-input  me-1" name="sex" required><span>Female</span>
            </div>
            <div class="col-6">
              <button type="submit" class="btn btn-success w-100"><i class="fas fa-upload"></i> Submit</button>
            </div>
          </div>
          </div>
        </form>
      </div>
    <div class="col-12 bg-white">
      <jsp:include page="Footer.jsp" />
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