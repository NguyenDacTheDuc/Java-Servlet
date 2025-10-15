package controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.ClassDto;
import model.dto.StudentDto;
import model.entity.Class;
import service.ClassService;
import service.StudentService;

@WebServlet("/client/student")
public class StudentClient extends HttpServlet {
  private final StudentService studentService = new StudentService();
  private final ClassService classService = new ClassService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    String idtc = req.getParameter("idtc");
    List<StudentDto> list = studentService.getAll();
    if (id != null) {
      Class c = classService.getById(Integer.parseInt(id));
      list.removeIf(s -> !s.getClassName().equals(c.getName()));
      req.setAttribute("className", c.getName());
    } else if (idtc != null) {
      List<ClassDto> classList = classService.getAll();
      for (ClassDto clas : classList) {
        if (Integer.parseInt(idtc) == clas.getTeacherId()) {
          Class c = classService.getById(clas.getId());
          list.removeIf(s -> !s.getClassName().equals(c.getName()));
          break;
        }
      }
    }
    req.setAttribute("studentList", list);
    req.getRequestDispatcher("Student.jsp").forward(req, res);
  }
}
