package service;

import java.util.List;

import model.dao.RegisterClassDao;
import model.entity.RegisterClass;

public class RegisterClassService {
  private final RegisterClassDao registerClassDao;

  public RegisterClassService() {
    registerClassDao = new RegisterClassDao();
  }

  public List<RegisterClass> getAll() {
    try {
      return registerClassDao.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void add(RegisterClass rc) {
    try {
      registerClassDao.add(rc);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    try {
      registerClassDao.detele(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
