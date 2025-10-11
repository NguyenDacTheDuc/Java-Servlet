package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/admin/users/update")
public class UserEdit extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    UserService userService = new UserService();
    User user = null;
    user = userService.getById(Integer.parseInt(id));
    req.setAttribute("user", user);
    req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    String name = req.getParameter("username").trim();
    String email = req.getParameter("email").trim();
    String password = req.getParameter("password");
    String enPassword = req.getParameter("enPassword");
    String role = req.getParameter("role");
    UserService userService = new UserService();
    User user = null;
    user = userService.getById(Integer.parseInt(id));
    req.setAttribute("user", user);
    if (name.equals("")) {
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorN", "Please enter a valid username");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    if (email.equals("")) {
      req.setAttribute("name", name);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorE", "Please enter a valid email");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    if (password.equals("")) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorP", "Please enter a valid password");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    if (password.length() < 6) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("errorPa", "Password must be at least 6 characters");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    if (enPassword.equals("")) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("errorEn", "Please enter a valid enpassword");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    if (!password.equals(enPassword)) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("errorEnP", "Passwords do not match");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    if (role == null) {
      req.setAttribute("name", name);
      req.setAttribute("email", email);
      req.setAttribute("password", password);
      req.setAttribute("enPassword", enPassword);
      req.setAttribute("errorR", "Please select a role");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    List<User> listUser = new ArrayList<>();
    listUser = userService.getAll();
    boolean check = false;
    if (id == null || id.trim().isEmpty()) {
      req.setAttribute("error", "User ID is required");
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
      return;
    }
    for (User userl : listUser) {
      if (userl.getId() == Integer.parseInt(id)) {
        continue;
      }
      if (userl.getUsername().equals(name) || userl.getEmail().equals(email)) {
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
      req.getRequestDispatcher("UserEdit.jsp").forward(req, resp);
    } else {
      User userUpdate = new User();
      userUpdate.setId(Integer.parseInt(id));
      userUpdate.setUsername(name);
      userUpdate.setEmail(email);
      userUpdate.setPassword(password);
      userUpdate.setRole(role);
      userService.update(userUpdate);
      resp.sendRedirect(req.getContextPath() + "/admin/user");
    }
  }
}
