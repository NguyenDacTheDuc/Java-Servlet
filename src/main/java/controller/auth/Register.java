package controller.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/register")
public class Register extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getRequestDispatcher("Register.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String enPassword = req.getParameter("enPassword");
    if (!password.equals(enPassword)) {
      req.setAttribute("name", username);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("error", "Passwords do not match");
      req.getRequestDispatcher("Register.jsp").forward(req, res);
    } else {
      boolean check = false;
      UserService userService = new UserService();
      List<User> listUser = userService.getAll();
      User user = new User();
      user.setUsername(username);
      user.setEmail(email);
      user.setPassword(password);
      for (User u : listUser) {
        if (u.getUsername().equals(username)) {
          check = true;
          req.setAttribute("error", "Username is already taken");
          break;
        }
        if (u.getEmail().equals(email)) {
          check = true;
          req.setAttribute("error", "Email is already in use");
          break;
        }
      }
      if (check) {
        req.setAttribute("name", username);
        req.setAttribute("email", email);
        req.setAttribute("password", password);
        req.setAttribute("enPassword", enPassword);
        req.getRequestDispatcher("Register.jsp").forward(req, res);
      } else {
        user.setRole("viewer");
        userService.add(user);
        req.setAttribute("success", "Account created successfully");
        req.getRequestDispatcher("Login.jsp").forward(req, res);
      }
    }
  }
}