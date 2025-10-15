package controller.admin.user;

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
  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    userService.delete(Integer.parseInt(id));
    res.setStatus(200);
  }
}
