package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GradeService;

@WebServlet("/client/score")
public class ScoreClient extends HttpServlet {
  private final GradeService gradeService = new GradeService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    req.setAttribute("listSubjectScore", gradeService.getGradeByStudentId(Integer.parseInt(id)));
    req.getRequestDispatcher("Score.jsp").forward(req, res);
  }
}
