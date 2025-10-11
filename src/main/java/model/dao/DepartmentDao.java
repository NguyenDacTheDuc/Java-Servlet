package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.entity.Department;

public class DepartmentDao {
  public List<Department> getAll() throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from department");
    ResultSet rs = ps.executeQuery();
    List<Department> list = new ArrayList<>();
    while (rs.next()) {
      Department d = new Department();
      d.setId(rs.getInt("id"));
      d.setName(rs.getString("name"));
      list.add(d);
    }
    return list;
  }

  public Department getById(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from department where id = ?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    Department department = null;
    if (rs.next()) {
      department = new Department();
      department.setId(rs.getInt("id"));
      department.setName(rs.getString("name"));
    }
    return department;
  }

  public List<Department> findByName(String name) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from department where name like ?");
    ps.setString(1, "%" + name + "%");
    ResultSet rs = ps.executeQuery();
    List<Department> list = new ArrayList<>();
    while (rs.next()) {
      Department department = new Department();
      department.setId(rs.getInt("id"));
      department.setName(rs.getString("name"));
      list.add(department);
    }
    return list;
  }

  public void add(Department d) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("insert into department (name) values (?)");
    ps.setString(1, d.getName());
    ps.executeUpdate();
  }

  public void update(Department d) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("update department set name = ? where id = ?");
    ps.setString(1, d.getName());
    ps.setInt(2, d.getId());
    ps.executeUpdate();
  }

  public void delete(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from department where id = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}