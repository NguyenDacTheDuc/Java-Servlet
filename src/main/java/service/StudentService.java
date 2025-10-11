package service;

import java.util.List;

import model.dao.StudentDao;
import model.dto.StudentDto;
import model.entity.Student;

public class StudentService {
  private final StudentDao student;

  public StudentService() {
    student = new StudentDao();
  }

  public List<StudentDto> getAll() {
    try {
      return student.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<StudentDto> findByName(String name) {
    try {
      return student.findByName(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Student getById(int id) {
    try {
      return student.getById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int add(Student s) {
    try {
      return student.add(s);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Student s) {
    try {
      student.update(s);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int i) {
    try {
      student.delete(i);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
