package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/admin/user")
public class UserController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UserService userService = new UserService();
    User user = (User) req.getSession().getAttribute("user");
    List<User> listUser = userService.getAll();
    if (user.getRole().equals("master")) {
      listUser.removeIf(u -> u.getRole().equals("master"));
    } else {
      listUser.removeIf(u -> u.getRole().equals("admin") || u.getRole().equals("master"));
    }
    req.setAttribute("listUser", listUser);
    req.getRequestDispatcher("users/User.jsp").forward(req, resp);
  }
}
