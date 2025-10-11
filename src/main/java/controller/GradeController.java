package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Grades;
import service.GradeService;

@WebServlet("/admin/grade")
public class GradeController extends HttpServlet {
  private final GradeService gradeService = new GradeService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String idst = req.getParameter("idst");
    String idsu = req.getParameter("idsu");
    if (idst != null) {
      req.getSession().setAttribute("id", idst);
      req.setAttribute("listSubjectScore", gradeService.getGradeByStudentId(Integer.parseInt(idst)));
      req.getRequestDispatcher("student/StudentScore.jsp").forward(req, res);
      return;
    }
    String name = req.getParameter("name");
    req.setAttribute("id", idsu);
    req.getSession().setAttribute("name", name);
    req.setAttribute("listStudentScore", gradeService.getGradeBySubjectId(Integer.parseInt(idsu)));
    req.getRequestDispatcher("subject/SubjectScore.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String studentId = req.getParameter("studentId");
    String subjectId = req.getParameter("subjectId");
    String grade = req.getParameter("grade");
    String name = req.getParameter("findName");
    if (name != null) {
      if (req.getParameter("findByStudent") != null) {
        String idsu = req.getParameter("idsu");
        req.setAttribute("listStudentScore",
            gradeService.findByStudent(name, Integer.parseInt(idsu)));
        req.getRequestDispatcher("subject/SubjectScore.jsp").forward(req, res);
      }
      if (req.getParameter("findBySubject") != null) {
        String idst = req.getParameter("idst");
        req.setAttribute("nameSubject", req.getParameter("nameSubject"));

        req.setAttribute("listSubjectScore",
            gradeService.findBySubject(name, Integer.parseInt(idst)));
        req.getRequestDispatcher("student/StudentScore.jsp").forward(req, res);
      }
      return;
    }
    if (Float.parseFloat(grade) < 0 || Float.parseFloat(grade) > 10) {
      return;
    }
    Grades g = new Grades();
    g.setIdStudent(Integer.parseInt(studentId));
    g.setIdSubject(Integer.parseInt(subjectId));
    g.setGrade(Float.parseFloat(grade));
    gradeService.update(g);
  }
}
