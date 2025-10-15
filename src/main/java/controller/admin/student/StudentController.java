package controller.admin.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GradeService;
import service.StudentService;

@WebServlet("/admin/student")
public class StudentController extends HttpServlet {
  private final StudentService student = new StudentService();
  private final GradeService grade = new GradeService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("listStudent", student.getAll());
    req.getRequestDispatcher("student/Student.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String find = req.getParameter("findName") == null ? "" : req.getParameter("findName").trim();
    if (find != null && !find.isEmpty()) {
      req.setAttribute("listStudent", student.findByName(find));
      req.getRequestDispatcher("student/Student.jsp").forward(req, res);
    } else {
      doGet(req, res);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    grade.deleteByStudentId(Integer.parseInt(id));
    student.delete(Integer.parseInt(id));
    res.setStatus(200);
  }
}
