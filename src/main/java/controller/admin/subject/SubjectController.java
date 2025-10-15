package controller.admin.subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.StudentDto;
import model.entity.Grades;
import model.entity.Subject;
import service.GradeService;
import service.StudentService;
import service.SubjectService;

@WebServlet("/admin/subject")
public class SubjectController extends HttpServlet {
  private final SubjectService ss = new SubjectService();
  private final StudentService student = new StudentService();
  private final GradeService grade = new GradeService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("listSubject", ss.getAll());
    req.getRequestDispatcher("subject/Subject.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String action = req.getParameter("action");
    String name = req.getParameter("name") == null ? "" : req.getParameter("name").trim();
    String find = req.getParameter("findName") == null ? "" : req.getParameter("findName").trim();
    if (find != null && !find.isEmpty()) {
      req.setAttribute("listSubject", ss.findByName(find));
      req.getRequestDispatcher("subject/Subject.jsp").forward(req, res);
      return;
    }
    if ("update".equals(action)) {
      String id = req.getParameter("id");
      if (name.isEmpty()) {
        req.setAttribute("error", "Subject name is required");
        req.setAttribute("listSubject", ss.getAll());
        req.getRequestDispatcher("subject/Subject.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (Subject s : ss.getAll()) {
        if (s.getId() != Integer.parseInt(id) && s.getName().equals(name)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("error", "Subject name already exists!");
        req.setAttribute("listSubject", ss.getAll());
        req.getRequestDispatcher("subject/Subject.jsp").forward(req, res);
      } else {
        Subject s = new Subject();
        s.setId(Integer.parseInt(id));
        s.setName(name);
        ss.update(s);
        res.setStatus(200);
      }

    } else {
      if (name.isEmpty()) {
        req.setAttribute("error", "Subject name is required");
        req.setAttribute("listSubject", ss.getAll());
        req.getRequestDispatcher("subject/Subject.jsp").forward(req, res);
        return;
      }
      boolean exists = false;
      for (Subject s : ss.getAll()) {
        if (s.getName().equals(name)) {
          exists = true;
          break;
        }
      }
      if (exists) {
        req.setAttribute("error", "Subject name already exists!");
        req.setAttribute("listSubject", ss.getAll());
        req.getRequestDispatcher("subject/Subject.jsp").forward(req, res);
        return;
      }
      Subject s = new Subject();
      s.setName(name);
      int subjectId = ss.add(s);
      for (StudentDto studentDto : student.getAll()) {
        Grades g = new Grades();
        g.setIdSubject(subjectId);
        g.setIdStudent(studentDto.getId());
        g.setGrade(0.0f);
        grade.add(g);
      }
      res.sendRedirect(req.getContextPath() + "/admin/subject");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    grade.deleteBySubjectId(Integer.parseInt(id));
    ss.delete(Integer.parseInt(id));
    res.setStatus(200);
  }
}
