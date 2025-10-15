package controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Teacher;
import service.TeacherService;

@WebServlet("/client/teacher")
public class TeacherClient extends HttpServlet {
  private final TeacherService teacherService = new TeacherService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    List<Teacher> list = teacherService.getAll();
    list.removeIf(t -> t.getStatus().equals("Inactive"));
    req.setAttribute("teacherList", list);
    req.getRequestDispatcher("Teacher.jsp").forward(req, res);
  }
}
