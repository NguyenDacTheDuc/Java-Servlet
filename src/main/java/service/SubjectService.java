package service;

import java.util.List;

import model.dao.SubjectDao;
import model.entity.Subject;

public class SubjectService {
  private final SubjectDao subjectDao;

  public SubjectService() {
    subjectDao = new SubjectDao();
  }

  public List<Subject> getAll() {
    try {
      return subjectDao.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Subject getById(int id) {
    try {
      return subjectDao.getById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Subject> findByName(String name) {
    try {
      return subjectDao.findByName(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int add(Subject s) {
    try {
      return subjectDao.add(s);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Subject s) {
    try {
      subjectDao.update(s);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    try {
      subjectDao.delete(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
