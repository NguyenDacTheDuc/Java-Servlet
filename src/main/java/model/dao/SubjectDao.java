package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.entity.Subject;

public class SubjectDao {
  public List<Subject> getAll() throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from subject");
    ResultSet rs = ps.executeQuery();
    List<Subject> list = new ArrayList<>();
    while (rs.next()) {
      Subject s = new Subject();
      s.setId(rs.getInt("id"));
      s.setName(rs.getString("name"));
      list.add(s);
    }
    return list;
  }

  public Subject getById(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from subject where id = ?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    Subject s = new Subject();
    if (rs.next()) {
      s.setId(rs.getInt("id"));
      s.setName(rs.getString("name"));
    }
    return s;
  }

  public List<Subject> findByName(String name) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from subject where name like ?");
    ps.setString(1, "%" + name + "%");
    ResultSet rs = ps.executeQuery();
    List<Subject> list = new ArrayList<>();
    while (rs.next()) {
      Subject s = new Subject();
      s.setId(rs.getInt("id"));
      s.setName(rs.getString("name"));
      list.add(s);
    }
    return list;
  }

  public int add(Subject s) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("insert into subject (name) values (?)",
        Statement.RETURN_GENERATED_KEYS);
    ps.setString(1, s.getName());
    ps.executeUpdate();
    ResultSet rs = ps.getGeneratedKeys();
    if (rs.next()) {
      return rs.getInt(1);
    }
    return -1;
  }

  public void update(Subject s) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("update subject set name = ? where id =?");
    ps.setString(1, s.getName());
    ps.setInt(2, s.getId());
    ps.executeUpdate();
  }

  public void delete(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from subject where id = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
