package service;

import java.util.List;

import model.dao.GradesDao;
import model.dto.GradeDto;
import model.entity.Grades;

public class GradeService {
  private final GradesDao grade;

  public GradeService() {
    grade = new GradesDao();
  }

  public List<GradeDto> getGradeByStudentId(int id) {
    try {
      return grade.getGradeByStudentId(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<GradeDto> getGradeBySubjectId(int id) {
    try {
      return grade.getGradeBySubjectId(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<GradeDto> findByStudent(String name, int id) {
    try {
      return grade.findByStudent(name, id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<GradeDto> findBySubject(String name, int id) {
    try {
      return grade.findBySubject(name, id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void add(Grades g) {
    try {
      grade.add(g);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Grades g) {
    try {
      grade.update(g);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteByStudentId(int id) {
    try {
      grade.deleteByStudentId(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteBySubjectId(int id) {
    try {
      grade.deleteBySubjectId(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
