package controller.client;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.ClassDto;
import model.entity.RegisterClass;
import model.entity.Teacher;
import service.ClassService;
import service.RegisterClassService;
import service.TeacherService;

@WebServlet("/client/registerclass")
public class RegisterClassController extends HttpServlet {
  private final RegisterClassService registerClassService = new RegisterClassService();
  private final TeacherService teacherService = new TeacherService();
  private final ClassService classService = new ClassService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String id = req.getParameter("id");
    String idtc = req.getParameter("idtc");
    req.setAttribute("classId", id);
    if (idtc != null) {
      Teacher t = teacherService.getById(Integer.parseInt(idtc));
      for (ClassDto c : classService.getAll()) {
        if (c.getTeacherId() == t.getId()) {
          req.setAttribute("classId", c.getId());
        }
      }
    }
    req.getRequestDispatcher("RegisterClass.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("name").trim();
    String birthday = req.getParameter("birthday").trim();
    String phone = req.getParameter("phone").trim();
    String address = req.getParameter("address").trim();
    String sex = req.getParameter("sex").trim();
    String userId = req.getParameter("userId").trim();
    String classId = req.getParameter("classId").trim();
    RegisterClass registerClass = new RegisterClass();
    registerClass.setUserId(Integer.parseInt(userId));
    registerClass.setBirthday(LocalDate.parse(birthday));
    registerClass.setAddress(address);
    registerClass.setClassId(Integer.parseInt(classId));
    registerClass.setName(name);
    registerClass.setPhone(phone);
    registerClass.setSex(sex);
    registerClassService.add(registerClass);
    res.sendRedirect(req.getContextPath() + "/client/department");
  }
}
