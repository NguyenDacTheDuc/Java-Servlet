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

@WebServlet("/admin/users/add")
public class UserAdd extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String enPassword = req.getParameter("enPassword");
    String role = req.getParameter("role");
    if (name.equals("")) {
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorN", "Please enter a valid username");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else if (email.equals("")) {
      req.setAttribute("name", name);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorE", "Please enter a valid email");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else if (password.equals("")) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorP", "Please enter a valid password");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else if (password.length() < 6) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("errorPa", "Password must be at least 6 characters");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else if (enPassword.equals("")) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("errorEn", "Please enter a valid enpassword");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else if (!password.equals(enPassword)) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("errorEnP", "Passwords do not match");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else if (role == null) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorR", "Please select a role");
      req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
    } else {
      UserService userService = new UserService();
      List<User> listUser = userService.getAll();
      boolean check = false;
      for (User user : listUser) {
        if (user.getUsername().equals(name) || user.getEmail().equals(email)) {
          check = true;
          req.setAttribute("error", "User or Email already exists");
          break;
        }
      }
      if (check) {
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("password", password);
        req.setAttribute("enPassword", enPassword);
        req.getRequestDispatcher("UserAdd.jsp").forward(req, resp);
      } else {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userService.add(user);
        resp.sendRedirect(req.getContextPath() + "/admin/user");
      }
    }
  }
}
