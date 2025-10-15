package controller.admin.department;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Department;
import service.DepartmentService;

@WebServlet("/admin/department")
public class DepartmentController extends HttpServlet {
  private final DepartmentService ds = new DepartmentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    List<Department> listDepartment = ds.getAll();
    req.setAttribute("listDepartment", listDepartment);
    req.getRequestDispatcher("department/Department.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String action = req.getParameter("action");
    String name = req.getParameter("name") == null ? "" : req.getParameter("name").trim();
    String find = req.getParameter("findName") == null ? "" : req.getParameter("findName").trim();
    if (find != null && !find.isEmpty()) {
      req.setAttribute("listDepartment", ds.findByName(find));
      req.getRequestDispatcher("department/Department.jsp").forward(req, res);
      return;
    }
    if ("update".equals(action)) {
      String id = req.getParameter("id");
      if (name.isEmpty()) {
        req.setAttribute("error", "Department name is required!");
        req.setAttribute("listDepartment", ds.getAll());
        req.getRequestDispatcher("department/Department.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (Department d : ds.getAll()) {
        if (d.getId() != Integer.parseInt(id) && d.getName().equals(name)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("error", "Department already exists!");
        req.setAttribute("listDepartment", ds.getAll());
        req.getRequestDispatcher("department/Department.jsp").forward(req, res);
        return;
      } else {
        Department department = new Department();
        department.setId(Integer.parseInt(id));
        department.setName(name);
        ds.update(department);
        res.setStatus(200);
      }
    } else {
      if (name.isEmpty()) {
        req.setAttribute("listDepartment", ds.getAll());
        req.setAttribute("error", "Department name is required");
        req.getRequestDispatcher("department/Department.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (Department d : ds.getAll()) {
        if (d.getName().equals(name)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("listDepartment", ds.getAll());
        req.setAttribute("error", "Department already exists");
        req.getRequestDispatcher("department/Department.jsp").forward(req, res);
        return;
      } else {
        Department newDepartment = new Department();
        newDepartment.setName(name);
        ds.add(newDepartment);
        res.setStatus(200);
      }
    }
    res.sendRedirect(req.getContextPath() + "/admin/department");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    ds.delete(Integer.parseInt(id));
    res.setStatus(200);
  }
}
