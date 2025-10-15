package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Class;
import model.entity.Student;
import model.entity.User;
import service.ClassService;
import service.StudentService;

@WebServlet("/client/about")
public class AboutClient extends HttpServlet {
  private final StudentService studentService = new StudentService();
  private final ClassService classService = new ClassService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    User user = (User) req.getSession().getAttribute("user");
    Student student = studentService.getById(user.getStudentId());
    Class clas = classService.getById(student.getClassId());
    req.setAttribute("information", student);
    req.setAttribute("clas", clas.getName());
    req.getRequestDispatcher("About.jsp").forward(req, res);
  }
}
