package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Grades;
import model.entity.Student;
import model.entity.Subject;
import service.ClassService;
import service.GradeService;
import service.StudentService;
import service.SubjectService;

@WebServlet("/admin/student/form")
public class StudentAUController extends HttpServlet {
  private final StudentService student = new StudentService();
  private final ClassService listClass = new ClassService();
  private final SubjectService listSubject = new SubjectService();
  private final GradeService grade = new GradeService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    if (id != null) {
      Student s = student.getById(Integer.parseInt(id));
      req.setAttribute("id", String.valueOf(s.getId()));
      dataArchive(req, s.getName(), s.getBirthday().toString(), s.getPhone(), s.getEmail(), s.getAddress(), s.getSex(),
          s.getStatus(), String.valueOf(s.getClassId()));
    }
    req.setAttribute("listClass", listClass.getAll());
    req.getRequestDispatcher("StudentForm.jsp").forward(req, res);
  }

  private void dataArchive(HttpServletRequest req, String name, String birthday, String phone, String email,
      String address, String sex, String status, String classId) {
    req.setAttribute("name", name);
    req.setAttribute("birthday", birthday);
    req.setAttribute("phone", phone);
    req.setAttribute("email", email);
    req.setAttribute("address", address);
    req.setAttribute("sex", sex);
    req.setAttribute("status", status);
    req.setAttribute("classId", classId);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("name") == null ? "" : req.getParameter("name").trim();
    String birthday = req.getParameter("birthday");
    String phone = req.getParameter("phone");
    String email = req.getParameter("email") == null ? "" : req.getParameter("email").trim();
    String address = req.getParameter("address");
    String sex = req.getParameter("sex");
    String status = req.getParameter("status");
    String classId = req.getParameter("class");
    String action = req.getParameter("action");
    dataArchive(req, name, birthday, phone, email, address, sex, status, classId);
    if (name.isEmpty()) {
      req.setAttribute("error", "Name student is required!");
    } else if (birthday.isEmpty()) {
      req.setAttribute("error", "Birthday is required!");
    } else if (phone.isEmpty()) {
      req.setAttribute("error", "Phone is required!");
    } else if (!phone.startsWith("0")) {
      req.setAttribute("error", "Phone number must start with 0!");
    } else if (email.isEmpty()) {
      req.setAttribute("error", "Email is required!");
    } else if (address == null || address.isEmpty()) {
      req.setAttribute("error", "Address is required!");
    } else if (sex == null || sex.isEmpty()) {
      req.setAttribute("error", "Sex is required!");
    } else if (status == null || status.isEmpty()) {
      req.setAttribute("error", "Status is required!");
    } else if (classId == null || classId.isEmpty()) {
      req.setAttribute("error", "Class is required!");
    }
    if (req.getAttribute("error") != null) {
      doGet(req, res);
      return;
    }
    if ("update".equals(action)) {
      String id = req.getParameter("id");
      Student s = new Student();
      s.setAddress(address);
      s.setBirthday(LocalDate.parse(birthday));
      s.setClassId(Integer.parseInt(classId));
      s.setEmail(email);
      s.setName(name);
      s.setPhone(phone);
      s.setSex(sex);
      s.setStatus(status);
      s.setId(Integer.parseInt(id));
      student.update(s);
      res.sendRedirect(req.getContextPath() + "/admin/student");

    } else {
      Student s = new Student();
      s.setAddress(address);
      s.setBirthday(LocalDate.parse(birthday));
      s.setClassId(Integer.parseInt(classId));
      s.setEmail(email);
      s.setName(name);
      s.setPhone(phone);
      s.setSex(sex);
      s.setStatus(status);
      int studentId = student.add(s);
      for (Subject sub : listSubject.getAll()) {
        Grades g = new Grades();
        g.setIdStudent(studentId);
        g.setIdSubject(sub.getId());
        g.setGrade(0.0f);
        grade.add(g);
      }
      res.sendRedirect(req.getContextPath() + "/admin/student");
    }
  }
}
