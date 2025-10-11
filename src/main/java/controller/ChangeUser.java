package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/client/change")
public class ChangeUser extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getRequestDispatcher("Change.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String oldPassword = req.getParameter("oldPassword");
    String newPassword = req.getParameter("newPassword");
    String enPassword = req.getParameter("enPassword");
    User user = (User) req.getSession().getAttribute("user");
    if (newPassword.equals(enPassword) && oldPassword.equals(user.getPassword())) {
      user.setPassword(newPassword);
      UserService userService = new UserService();
      userService.update(user);
      res.sendRedirect(req.getContextPath() + "/client/home");
    } else {
      req.setAttribute("error", "Error");
      req.getRequestDispatcher("/client/Change.jsp").forward(req, res);
    }
  }
}
