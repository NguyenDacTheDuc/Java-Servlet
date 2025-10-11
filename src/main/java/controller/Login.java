package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getRequestDispatcher("Login.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    UserService userService = new UserService();
    User user = userService.login(username, password);
    if (user == null) {
      req.setAttribute("error", "Wrong username or password");
      doGet(req, res);
    } else {
      req.getSession().setAttribute("user", user);
      if (user.getRole().equals("master") || user.getRole().equals("admin")) {
        res.sendRedirect(req.getContextPath() + "/admin/home");
      } else {
        req.getSession().setAttribute("registered", user.getStudentId());
        res.sendRedirect(req.getContextPath() + "/client/home");
      }
    }
  }
}
