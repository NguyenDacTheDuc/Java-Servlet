package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.entity.User;

public class UserDao {
  public User login(String username, String password) throws Exception {
    User user = null;
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
    ps.setString(1, username);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      user = new User();
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setRole(rs.getString("role"));
      user.setEmail(rs.getString("email"));
      user.setStudentId(rs.getInt("id_student"));
    }
    return user;
  }

  public User forget(String username, String email) throws Exception {
    User user = null;
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND email = ?");
    ps.setString(1, username);
    ps.setString(2, email);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      user = new User();
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setRole(rs.getString("role"));
      user.setEmail(rs.getString("email"));
      user.setStudentId(rs.getInt("id_student"));
    }
    return user;
  }

  public User getByName(String name) throws Exception {
    User user = null;
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
    ps.setString(1, name);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      user = new User();
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setRole(rs.getString("role"));
      user.setEmail(rs.getString("email"));
      user.setStudentId(rs.getInt("id_student"));
    }
    return user;
  }

  public List<User> getAll() throws Exception {
    List<User> users = new ArrayList<>();
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      User user = new User();
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setRole(rs.getString("role"));
      user.setEmail(rs.getString("email"));
      user.setStudentId(rs.getInt("id_student"));
      users.add(user);
    }
    return users;
  }

  public User getById(int id) throws Exception {
    User user = new User();
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setRole(rs.getString("role"));
      user.setEmail(rs.getString("email"));
      user.setStudentId(rs.getInt("id_student"));
    }
    return user;
  }

  public User getByStudentId(int id) throws Exception {
    User user = new User();
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE id_student = ?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      user.setId(rs.getInt("id"));
      user.setUsername(rs.getString("username"));
      user.setPassword(rs.getString("password"));
      user.setRole(rs.getString("role"));
      user.setEmail(rs.getString("email"));
      user.setStudentId(rs.getInt("id_student"));
    }
    return user;
  }

  public void add(User user) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn
        .prepareStatement("INSERT INTO user (username, password, role, email) VALUES (?, ?, ?, ?)");
    ps.setString(1, user.getUsername());
    ps.setString(2, user.getPassword());
    ps.setString(3, user.getRole());
    ps.setString(4, user.getEmail());
    ps.executeUpdate();
  }

  public void update(User user) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn
        .prepareStatement("UPDATE user SET username = ?, password = ?, role = ?, email = ? WHERE id = ?");
    ps.setString(1, user.getUsername());
    ps.setString(2, user.getPassword());
    ps.setString(3, user.getRole());
    ps.setString(4, user.getEmail());
    ps.setInt(5, user.getId());
    ps.executeUpdate();
  }

  public void updateWithStudentId(User user, int id) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn
        .prepareStatement(
            "UPDATE user SET username = ?, password = ?, role = ?, email = ?, id_student = ? WHERE id = ?");
    ps.setString(1, user.getUsername());
    ps.setString(2, user.getPassword());
    ps.setString(3, user.getRole());
    ps.setString(4, user.getEmail());
    ps.setInt(5, id);
    ps.setInt(6, user.getId());
    ps.executeUpdate();
  }

  public void delete(int id) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("DELETE FROM user WHERE id = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
