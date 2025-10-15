package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ClassService;

@WebServlet("/client/class")
public class ClassClient extends HttpServlet {
  private final ClassService classService = new ClassService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("department");
    req.setAttribute("departmentName", name);
    req.setAttribute("classList", classService.getByDepartment(name));
    System.out.println(name);
    req.getRequestDispatcher("Class.jsp").forward(req, res);
  }
}
