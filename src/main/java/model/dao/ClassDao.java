package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.dto.ClassDto;
import model.entity.Class;

public class ClassDao {
  public List<ClassDto> getAll() throws Exception {
    List<ClassDto> classes = new ArrayList<>();
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement(
        "SELECT class.id as id, class.name as name, teacher.id as ti, teacher.name as tname, department.id as di, department.name as dname "
            + "FROM class join teacher on class.id_teacher = teacher.id join department on class.id_department = department.id");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      ClassDto clas = new ClassDto();
      clas.setId(rs.getInt("id"));
      clas.setName(rs.getString("name"));
      clas.setTeacherId(rs.getInt("ti"));
      clas.setTeacherName(rs.getString("tname"));
      clas.setDepartmentId(rs.getInt("di"));
      clas.setDepartmentName(rs.getString("dname"));
      classes.add(clas);
    }
    return classes;
  }

  public Class getById(int id) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("SELECT * FROM class where id = ?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    Class c = new Class();
    if (rs.next()) {
      c.setId(rs.getInt("id"));
      c.setName(rs.getString("name"));
      c.setIdDepartment(rs.getInt("id_department"));
      c.setIdTeacher(rs.getInt("id_teacher"));
    }
    return c;
  }

  public List<ClassDto> getByDepartment(String name) throws Exception {
    List<ClassDto> classes = new ArrayList<>();
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement(
        "SELECT class.id as id, class.name as name, teacher.id as ti, teacher.name as tname, department.id as di, department.name as dname "
            + "FROM class join teacher on class.id_teacher = teacher.id join department on class.id_department = department.id where department.name like ?");
    ps.setString(1, "%" + name + "%");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      ClassDto clas = new ClassDto();
      clas.setId(rs.getInt("id"));
      clas.setName(rs.getString("name"));
      clas.setTeacherId(rs.getInt("ti"));
      clas.setTeacherName(rs.getString("tname"));
      clas.setDepartmentId(rs.getInt("di"));
      clas.setDepartmentName(rs.getString("dname"));
      classes.add(clas);
    }
    return classes;
  }

  public List<ClassDto> findByName(String name) throws Exception {
    List<ClassDto> classes = new ArrayList<>();
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement(
        "SELECT class.id as id, class.name as name, teacher.id as ti, teacher.name as tname, department.id as di, department.name as dname "
            + "FROM class join teacher on class.id_teacher = teacher.id join department on class.id_department = department.id where class.name LIKE ?");
    ps.setString(1, "%" + name + "%");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      ClassDto clas = new ClassDto();
      clas.setId(rs.getInt("id"));
      clas.setName(rs.getString("name"));
      clas.setTeacherId(rs.getInt("ti"));
      clas.setTeacherName(rs.getString("tname"));
      clas.setDepartmentId(rs.getInt("di"));
      clas.setDepartmentName(rs.getString("dname"));
      classes.add(clas);
    }
    return classes;
  }

  public void add(Class clas) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement(
        "INSERT INTO class (name, id_teacher, id_department) VALUES (?, ?, ?)");
    ps.setString(1, clas.getName());
    ps.setInt(2, clas.getIdTeacher());
    ps.setInt(3, clas.getIdDepartment());
    ps.executeUpdate();
  }

  public void update(Class clas) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement(
        "UPDATE class SET name = ?, id_teacher = ?, id_department = ? WHERE id = ?");
    ps.setString(1, clas.getName());
    ps.setInt(2, clas.getIdTeacher());
    ps.setInt(3, clas.getIdDepartment());
    ps.setInt(4, clas.getId());
    ps.executeUpdate();
  }

  public void delete(int id) throws Exception {
    Connection conn = ConnectDB.getConnectDB();
    PreparedStatement ps = conn.prepareStatement("DELETE FROM class WHERE id = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
