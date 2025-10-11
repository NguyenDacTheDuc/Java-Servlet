package service;

import java.util.List;

import model.dao.TeacherDao;
import model.entity.Teacher;

public class TeacherService {
  private final TeacherDao teacherDao;

  public TeacherService() {
    teacherDao = new TeacherDao();
  }

  public List<Teacher> getAll() {
    try {
      return teacherDao.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Teacher getById(int id) {
    try {
      return teacherDao.getById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Teacher> filterTeacher() {
    try {
      return teacherDao.filterTeacher();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Teacher> findByName(String name) {
    try {
      return teacherDao.findByName(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void add(Teacher t) {
    try {
      teacherDao.add(t);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Teacher t) {
    try {
      teacherDao.update(t);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    try {
      teacherDao.delete(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
