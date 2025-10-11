package service;

import java.util.List;

import model.dao.DepartmentDao;
import model.entity.Department;

public class DepartmentService {
  private final DepartmentDao departmentDao;

  public DepartmentService() {
    departmentDao = new DepartmentDao();
  }

  public List<Department> getAll() {
    try {
      return departmentDao.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Department getById(int id) {
    try {
      return departmentDao.getById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Department> findByName(String name) {
    try {
      return departmentDao.findByName(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void add(Department d) {
    try {
      departmentDao.add(d);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Department d) {
    try {
      departmentDao.update(d);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    try {
      departmentDao.delete(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
