package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.dto.StudentDto;
import model.entity.Student;

public class StudentDao {
  private StudentDto mapResultSetToStudentDto(ResultSet rs) throws SQLException {
    StudentDto s = new StudentDto();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    s.setId(rs.getInt("i"));
    s.setAddress(rs.getString("a"));
    s.setBirthday(rs.getDate("b").toLocalDate().format(formatter));
    s.setClassId(rs.getInt("ci"));
    s.setClassName(rs.getString("cn"));
    s.setEmail(rs.getString("e"));
    s.setName(rs.getString("n"));
    s.setPhone(rs.getString("p"));
    s.setSex(rs.getString("s"));
    s.setStatus(rs.getString("st"));
    return s;
  }

  public List<StudentDto> getAll() throws Exception {
    List<StudentDto> list = new ArrayList<>();
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select student.id as i, student.name as n, student.birthday as b, student.phone as p, student.email as e, student.address as a, student.sex as s, student.status as st, class.name as cn, class.id as ci "
            + "from student join class on student.id_class=class.id");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      list.add(mapResultSetToStudentDto(rs));
    }
    return list;
  }

  public List<StudentDto> findByName(String name) throws Exception {
    List<StudentDto> list = new ArrayList<>();
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select student.id as i, student.name as n, student.birthday as b, student.phone as p, student.email as e, student.address as a, student.sex as s, student.status as st, class.name as cn, class.id as ci "
            + "from student join class on student.id_class=class.id where student.name like ?");
    ps.setString(1, "%" + name + "%");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      list.add(mapResultSetToStudentDto(rs));
    }
    return list;
  }

  public Student getById(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from student where id = ?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    Student s = new Student();
    if (rs.next()) {
      s.setId(rs.getInt("id"));
      s.setName(rs.getString("name"));
      s.setBirthday(rs.getDate("birthday").toLocalDate());
      s.setPhone(rs.getString("phone"));
      s.setEmail(rs.getString("email"));
      s.setAddress(rs.getString("address"));
      s.setSex(rs.getString("sex"));
      s.setStatus(rs.getString("status"));
      s.setClassId(rs.getInt("id_class"));
    }
    return s;
  }

  public int add(Student s) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "insert into student (name, birthday, phone, email, address, sex, status, id_class) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS);
    ps.setString(1, s.getName());
    ps.setDate(2, Date.valueOf(s.getBirthday()));
    ps.setString(3, s.getPhone());
    ps.setString(4, s.getEmail());
    ps.setString(5, s.getAddress());
    ps.setString(6, s.getSex());
    ps.setString(7, s.getStatus());
    ps.setInt(8, s.getClassId());
    ps.executeUpdate();
    ResultSet rs = ps.getGeneratedKeys();
    if (rs.next()) {
      return rs.getInt(1);
    }
    return -1;
  }

  public void update(Student s) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "update student set name=?, birthday=?, phone=?, email=?, address=?, sex=?, status=?, id_class=? "
            + "where id=?");
    ps.setString(1, s.getName());
    ps.setDate(2, Date.valueOf(s.getBirthday()));
    ps.setString(3, s.getPhone());
    ps.setString(4, s.getEmail());
    ps.setString(5, s.getAddress());
    ps.setString(6, s.getSex());
    ps.setString(7, s.getStatus());
    ps.setInt(8, s.getClassId());
    ps.setInt(9, s.getId());
    ps.executeUpdate();
  }

  public void delete(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from student where id=?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
