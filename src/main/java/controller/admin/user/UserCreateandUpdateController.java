package controller.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import service.UserService;

@WebServlet("/admin/users/form")
public class UserCreateandUpdateController extends HttpServlet {
  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    if (id != null) {
      User user = userService.getById(Integer.parseInt(id));
      req.setAttribute("id", user.getId());
      dataArchive(user.getUsername(), user.getEmail(), user.getPassword(), user.getPassword(), user.getRole(), req);
    }
    req.getRequestDispatcher("UserForm.jsp").forward(req, res);
  }

  private void dataArchive(String name, String email, String password, String enPassword, String role,
      HttpServletRequest req) {
    req.setAttribute("name", name);
    req.setAttribute("email", email);
    req.setAttribute("password", password);
    req.setAttribute("enPassword", enPassword);
    req.setAttribute("role", role);
  }

  private void setAll(User u, String name, String password, String role, String email) {
    u.setEmail(email);
    u.setRole(role);
    u.setPassword(password);
    u.setUsername(name);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String enPassword = req.getParameter("enPassword");
    String role = req.getParameter("role");
    String action = req.getParameter("action");
    dataArchive(name, email, password, enPassword, role, req);
    if (name.isEmpty()) {
      req.setAttribute("error", "Please enter a valid username");
    } else if (email.isEmpty()) {
      req.setAttribute("error", "Please enter a valid email");
    } else if (password.isEmpty()) {
      req.setAttribute("error", "Please enter a valid password");
    } else if (password.length() < 6) {
      req.setAttribute("error", "Password must be at least 6 characters");
    } else if (enPassword.isEmpty()) {
      req.setAttribute("error", "Please enter a valid enPassword");
    } else if (!password.equals(enPassword)) {
      req.setAttribute("error", "Passwords do not match");
    } else if (role == null || role.isEmpty()) {
      req.setAttribute("error", "Please select a role");
    }
    if (req.getAttribute("error") != null) {
      doGet(req, res);
      return;
    }
    User user = new User();
    if ("update".equals(action)) {
      String id = req.getParameter("id");
      user.setId(Integer.parseInt(id));
      setAll(user, name, password, role, email);
      for (User u : userService.getAll()) {
        if (u.getId() != Integer.parseInt(id)
            && (user.getUsername().equals(u.getUsername()) || user.getEmail().equals(u.getEmail()))) {
          req.setAttribute("error", "Account name or email already exists");
          doGet(req, res);
          return;
        }
      }
      userService.update(user);
    } else {
      setAll(user, name, enPassword, role, email);
      for (User u : userService.getAll()) {
        if (user.getUsername().equals(u.getUsername()) || user.getEmail().equals(u.getEmail())) {
          req.setAttribute("error", "Account name or email already exists");
          doGet(req, res);
          return;
        }
      }
      userService.add(user);
    }
    res.sendRedirect(req.getContextPath() + "/admin/user");
  }
}
