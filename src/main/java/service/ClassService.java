package service;

import java.util.List;

import model.dao.ClassDao;
import model.dto.ClassDto;
import model.entity.Class;

public class ClassService {
  private final ClassDao classDao;

  public ClassService() {
    classDao = new ClassDao();
  }

  public List<ClassDto> getAll() {
    try {
      return classDao.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Class getById(int id) {
    try {
      return classDao.getById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<ClassDto> getByDepartment(String name) {
    try {
      return classDao.getByDepartment(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<ClassDto> findByName(String name) {
    try {
      return classDao.findByName(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void add(Class n) {
    try {
      classDao.add(n);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Class n) {
    try {
      classDao.update(n);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    try {
      classDao.delete(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
