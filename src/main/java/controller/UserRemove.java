package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.StudentService;
import service.UserService;

@WebServlet("/admin/users/remove")
public class UserRemove extends HttpServlet {
  private final UserService userService = new UserService();
  private final StudentService studentService = new StudentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    User user = userService.getById(Integer.parseInt(id));
    if (user.getStudentId() != 0) {
      studentService.delete(user.getStudentId());
    }
    userService.delete(Integer.parseInt(id));
    resp.sendRedirect(req.getContextPath() + "/admin/user");
  }
}
