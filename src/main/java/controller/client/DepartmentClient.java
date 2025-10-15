package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DepartmentService;

@WebServlet("/client/department")
public class DepartmentClient extends HttpServlet {
  private final DepartmentService departmentService = new DepartmentService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("department", departmentService.getAll());
    req.getRequestDispatcher("Department.jsp").forward(req, res);
  }
}
