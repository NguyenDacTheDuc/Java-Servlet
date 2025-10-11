package service;

import java.util.List;

import model.dao.UserDao;
import model.entity.User;

public class UserService {
  private UserDao userDao;

  public UserService() {
    userDao = new UserDao();
  }

  public User login(String username, String password) {
    try {
      return userDao.login(username, password);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public User forget(String username, String email) {
    try {
      if (username == null || email == null)
        return null;
      return userDao.forget(username, email);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public User getByName(String name) {
    try {
      return userDao.getByName(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<User> getAll() {
    try {
      return userDao.getAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public User getById(int id) {
    try {
      return userDao.getById(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public User getByStudentId(int id) {
    try {
      return userDao.getByStudentId(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void add(User user) {
    try {
      userDao.add(user);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void update(User user) {
    try {
      userDao.update(user);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void updateWithStudentId(User user, int id) {
    try {
      userDao.updateWithStudentId(user, id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {
    try {
      userDao.delete(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
