package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.dto.GradeDto;
import model.entity.Grades;

public class GradesDao {
  public List<GradeDto> getGradeByStudentId(int idStudent) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select grades.id_student as ist, grades.id_subject as isu, grades.grade as g, subject.name sun , student.name as stn "
            +
            "from grades join subject on subject.id=grades.id_subject join student on student.id=grades.id_student " +
            "where id_student=?");
    ps.setInt(1, idStudent);
    ResultSet rs = ps.executeQuery();
    List<GradeDto> list = new ArrayList<>();
    while (rs.next()) {
      GradeDto g = new GradeDto();
      g.setStudentId(rs.getInt("ist"));
      g.setSubjectId(rs.getInt("isu"));
      g.setStudentName(rs.getString("stn"));
      g.setSubjectName(rs.getString("sun"));
      g.setGrade(rs.getFloat("g"));
      list.add(g);
    }
    return list;
  }

  public List<GradeDto> getGradeBySubjectId(int idSubject) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select grades.id_student as ist, grades.id_subject as isu, grades.grade as g, subject.name sun , student.name as stn "
            +
            "from grades join subject on subject.id=grades.id_subject join student on student.id=grades.id_student " +
            "where id_subject=?");
    ps.setInt(1, idSubject);
    ResultSet rs = ps.executeQuery();
    List<GradeDto> list = new ArrayList<>();
    while (rs.next()) {
      GradeDto g = new GradeDto();
      g.setStudentId(rs.getInt("ist"));
      g.setSubjectId(rs.getInt("isu"));
      g.setStudentName(rs.getString("stn"));
      g.setSubjectName(rs.getString("sun"));
      g.setGrade(rs.getFloat("g"));
      list.add(g);
    }
    return list;
  }

  public List<GradeDto> findByStudent(String name, int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select grades.id_student as ist, grades.id_subject as isu, grades.grade as g, subject.name sun , student.name as stn "
            +
            "from grades join subject on subject.id=grades.id_subject join student on student.id=grades.id_student " +
            "where student.name like ? and id_subject=?");
    ps.setString(1, "%" + name + "%");
    ps.setInt(2, id);
    ResultSet rs = ps.executeQuery();
    List<GradeDto> list = new ArrayList<>();
    while (rs.next()) {
      GradeDto g = new GradeDto();
      g.setStudentId(rs.getInt("ist"));
      g.setSubjectId(rs.getInt("isu"));
      g.setStudentName(rs.getString("stn"));
      g.setSubjectName(rs.getString("sun"));
      g.setGrade(rs.getFloat("g"));
      list.add(g);
    }
    return list;
  }

  public List<GradeDto> findBySubject(String name, int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "select grades.id_student as ist, grades.id_subject as isu, grades.grade as g, subject.name sun , student.name as stn "
            +
            "from grades join subject on subject.id=grades.id_subject join student on student.id=grades.id_student " +
            "where subject.name like ? and id_student=?");
    ps.setString(1, "%" + name + "%");
    ps.setInt(2, id);
    ResultSet rs = ps.executeQuery();
    List<GradeDto> list = new ArrayList<>();
    while (rs.next()) {
      GradeDto g = new GradeDto();
      g.setStudentId(rs.getInt("ist"));
      g.setSubjectId(rs.getInt("isu"));
      g.setStudentName(rs.getString("stn"));
      g.setSubjectName(rs.getString("sun"));
      g.setGrade(rs.getFloat("g"));
      list.add(g);
    }
    return list;
  }

  public void add(Grades g) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("insert into grades(id_student, id_subject, grade) values (?, ?, ?)");
    ps.setInt(1, g.getIdStudent());
    ps.setInt(2, g.getIdSubject());
    ps.setFloat(3, g.getGrade());
    ps.executeUpdate();
  }

  public void update(Grades g) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("update grades set grade=? where id_student=? and id_subject=?");
    ps.setFloat(1, g.getGrade());
    ps.setInt(2, g.getIdStudent());
    ps.setInt(3, g.getIdSubject());
    ps.executeUpdate();
  }

  public void deleteByStudentId(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from grades where id_student = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }

  public void deleteBySubjectId(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from grades where id_subject = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
