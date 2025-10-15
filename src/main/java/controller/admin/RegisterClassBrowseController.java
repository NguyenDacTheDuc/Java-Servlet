package controller.admin;

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
import model.entity.User;
import service.GradeService;
import service.RegisterClassService;
import service.StudentService;
import service.SubjectService;
import service.UserService;

@WebServlet("/admin/browse")
public class RegisterClassBrowseController extends HttpServlet {
  private final RegisterClassService registerClassService = new RegisterClassService();
  private final StudentService studentService = new StudentService();
  private final UserService userService = new UserService();
  private final SubjectService listSubject = new SubjectService();
  private final GradeService grade = new GradeService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("registerClassList", registerClassService.getAll());
    req.getRequestDispatcher("Browse.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String userId = req.getParameter("userId");
    String name = req.getParameter("name");
    String birthday = req.getParameter("birthday");
    String phone = req.getParameter("phone");
    String sex = req.getParameter("sex");
    String address = req.getParameter("address");
    String classId = req.getParameter("classId");
    Student student = new Student();
    User user = userService.getById(Integer.parseInt(userId));
    student.setAddress(address);
    student.setBirthday(LocalDate.parse(birthday));
    student.setClassId(Integer.parseInt(classId));
    student.setName(name);
    student.setSex(sex);
    student.setPhone(phone);
    student.setEmail(user.getEmail());
    student.setStatus("Active");
    int studentId = studentService.add(student);
    for (Subject sub : listSubject.getAll()) {
      Grades g = new Grades();
      g.setIdStudent(studentId);
      g.setIdSubject(sub.getId());
      g.setGrade(0.0f);
      grade.add(g);
    }
    userService.updateWithStudentId(user, studentId);
    registerClassService.delete(Integer.parseInt(userId));
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    registerClassService.delete(Integer.parseInt(id));
  }
}
