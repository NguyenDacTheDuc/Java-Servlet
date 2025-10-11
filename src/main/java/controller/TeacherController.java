package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Teacher;
import service.TeacherService;

@WebServlet("/admin/teacher")
public class TeacherController extends HttpServlet {
  private final TeacherService ts = new TeacherService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("listTeacher", ts.getAll());
    req.getRequestDispatcher("teacher/Teacher.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String action = req.getParameter("action");
    String name = req.getParameter("name") == null ? "" : req.getParameter("name").trim();
    String email = req.getParameter("email") == null ? "" : req.getParameter("email").trim();
    String find = req.getParameter("findName") == null ? "" : req.getParameter("findName").trim();
    if (find != null && !find.isEmpty()) {
      req.setAttribute("listTeacher", ts.findByName(find));
      req.getRequestDispatcher("teacher/Teacher.jsp").forward(req, res);
      return;
    }
    if ("update".equals(action)) {
      String id = req.getParameter("id");
      String status = req.getParameter("status");
      if (name.isEmpty() || email.isEmpty()) {
        req.setAttribute("listTeacher", ts.getAll());
        req.setAttribute("error", "Teacher name and email is required");
        req.getRequestDispatcher("teacher/Teacher.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (Teacher t : ts.getAll()) {
        if (t.getId() != Integer.parseInt(id) && (t.getName().equals(name) || t.getEmail().equals(email))) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("listTeacher", ts.getAll());
        req.setAttribute("error", "Teacher already exists");
        req.getRequestDispatcher("teacher/Teacher.jsp").forward(req, res);
      } else {
        Teacher teacher = new Teacher();
        teacher.setId(Integer.parseInt(id));
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setStatus(status);
        ts.update(teacher);
        res.setStatus(200);
      }

    } else {
      String status = "Active";
      if (name.isEmpty() || email.isEmpty()) {
        req.setAttribute("listTeacher", ts.getAll());
        req.setAttribute("error", "Teacher name or email is required");
        req.getRequestDispatcher("teacher/Teacher.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (Teacher t : ts.getAll()) {
        if (t.getName().equals(name) || t.getEmail().equals(email)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("listTeacher", ts.getAll());
        req.setAttribute("error", "Teacher already exists");
        req.getRequestDispatcher("teacher/Teacher.jsp").forward(req, res);
      } else {
        Teacher newTeacher = new Teacher();
        newTeacher.setName(name);
        newTeacher.setEmail(email);
        newTeacher.setStatus(status);
        ts.add(newTeacher);
        res.setStatus(200);
        res.sendRedirect(req.getContextPath() + "/admin/teacher");
      }
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    ts.delete(Integer.parseInt(id));
    res.setStatus(200);
  }
}
