package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.entity.Teacher;

public class TeacherDao {
  public List<Teacher> getAll() throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from teacher");
    ResultSet rs = ps.executeQuery();
    List<Teacher> list = new ArrayList<>();
    while (rs.next()) {
      Teacher t = new Teacher();
      t.setId(rs.getInt("id"));
      t.setName(rs.getString("name"));
      t.setEmail(rs.getString("email"));
      t.setStatus(rs.getString("status"));
      list.add(t);
    }
    return list;
  }

  public Teacher getById(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from teacher where id=?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    Teacher t = new Teacher();
    if (rs.next()) {
      t.setId(rs.getInt("id"));
      t.setName(rs.getString("name"));
      t.setEmail(rs.getString("email"));
      t.setStatus(rs.getString("status"));
    }
    return t;
  }

  public List<Teacher> findByName(String name) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from teacher where name like ?");
    ps.setString(1, "%" + name + "%");
    ResultSet rs = ps.executeQuery();
    List<Teacher> list = new ArrayList<>();
    while (rs.next()) {
      Teacher t = new Teacher();
      t.setId(rs.getInt("id"));
      t.setName(rs.getString("name"));
      t.setEmail(rs.getString("email"));
      t.setStatus(rs.getString("status"));
      list.add(t);
    }
    return list;
  }

  public List<Teacher> filterTeacher() throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select teacher.id as i, teacher.name as n, email, status from teacher " +
            "left join class on teacher.id=class.id_teacher " +
            "where teacher.status='Active' and class.id_teacher is null");
    ResultSet rs = ps.executeQuery();
    List<Teacher> list = new ArrayList<>();
    while (rs.next()) {
      Teacher t = new Teacher();
      t.setId(rs.getInt("i"));
      t.setName(rs.getString("n"));
      t.setEmail(rs.getString("email"));
      t.setStatus(rs.getString("status"));
      list.add(t);
    }
    return list;
  }

  public void add(Teacher t) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("insert into teacher (name, email, status) values (?, ?, ?)");
    ps.setString(1, t.getName());
    ps.setString(2, t.getEmail());
    ps.setString(3, t.getStatus());
    ps.executeUpdate();
  }

  public void update(Teacher t) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("update teacher set name = ?, email=?, status=? where id=?");
    ps.setString(1, t.getName());
    ps.setString(2, t.getEmail());
    ps.setString(3, t.getStatus());
    ps.setInt(4, t.getId());
    ps.executeUpdate();
  }

  public void delete(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from teacher where id = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
