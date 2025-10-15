package controller.admin.clas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.ClassDto;
import model.entity.Class;
import service.ClassService;
import service.DepartmentService;
import service.TeacherService;

@WebServlet("/admin/class")
public class ClassController extends HttpServlet {
  private final ClassService c = new ClassService();
  private final TeacherService t = new TeacherService();
  private final DepartmentService d = new DepartmentService();

  private void loadLists(HttpServletRequest req) throws ServletException, IOException {
    req.setAttribute("listTeacher", t.filterTeacher());
    req.setAttribute("listDepartment", d.getAll());
    req.setAttribute("listClass", c.getAll());
  }

  private void setAll(String name, int idDepartment, int idTeacher, Class clas) {
    clas.setName(name);
    clas.setIdDepartment(idDepartment);
    clas.setIdTeacher(idTeacher);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    loadLists(req);
    req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("name") == null ? "" : req.getParameter("name").trim();
    String idDepartment = req.getParameter("idDepartment") == null ? "" : req.getParameter("idDepartment").trim();
    String idTeacher = req.getParameter("idTeacher") == null ? "" : req.getParameter("idTeacher").trim();
    String find = req.getParameter("findName") == null ? "" : req.getParameter("findName").trim();
    String action = req.getParameter("action");
    if (find != null && !find.isEmpty()) {
      req.setAttribute("listTeacher", t.filterTeacher());
      req.setAttribute("listDepartment", d.getAll());
      req.setAttribute("listClass", c.findByName(find));
      req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
      return;
    }
    if ("update".equals(action)) {
      String id = req.getParameter("id");
      if (name.isEmpty()) {
        req.setAttribute("error", "Class name is required!");
        loadLists(req);
        req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (ClassDto cla : c.getAll()) {
        if (cla.getId() != Integer.parseInt(id) && cla.getName().equals(name)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("error", "Class already exists!");
        loadLists(req);
        req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
      } else {
        Class clas = new Class();
        clas.setId(Integer.parseInt(id));
        setAll(name, Integer.parseInt(idDepartment), Integer.parseInt(idTeacher), clas);
        c.update(clas);
      }
    } else {
      if (name.isEmpty()) {
        req.setAttribute("error", "Class name is required");
        loadLists(req);
        req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
        return;
      }
      if (idDepartment == null || idTeacher == null || idDepartment.isEmpty() || idTeacher.isEmpty()) {
        req.setAttribute("error", "Department or Teacher are required!");
        loadLists(req);
        req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (ClassDto cla : c.getAll()) {
        if (cla.getName().equals(name)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("error", "Class already exists");
        loadLists(req);
        req.getRequestDispatcher("clas/Class.jsp").forward(req, res);
      } else {
        Class clas = new Class();
        setAll(name, Integer.parseInt(idDepartment), Integer.parseInt(idTeacher), clas);
        c.add(clas);
      }
    }
    res.sendRedirect(req.getContextPath() + "/admin/class");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    c.delete(Integer.parseInt(id));
    res.setStatus(200);
  }
}