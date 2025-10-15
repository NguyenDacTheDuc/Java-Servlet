package controller.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/forget")
public class Forget extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getRequestDispatcher("Forget.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    UserService userService = new UserService();
    User user = userService.forget(username, email);
    if (user == null) {
      req.setAttribute("error", "Account never existed");
      req.getRequestDispatcher("Login.jsp").forward(req, res);
    } else {
      user.setPassword("123456");
      userService.update(user);
      req.setAttribute("success", "Account reset successful and password is 123456");
      req.getRequestDispatcher("Login.jsp").forward(req, res);
    }
  }
}
