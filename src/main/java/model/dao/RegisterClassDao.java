package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectDB;
import model.entity.RegisterClass;

public class RegisterClassDao {
  private RegisterClass mapResultSet(ResultSet rs) throws SQLException {
    RegisterClass rc = new RegisterClass();
    rc.setUserId(rs.getInt("id_user"));
    rc.setName(rs.getString("name"));
    rc.setBirthday(rs.getDate("birthday").toLocalDate());
    rc.setSex(rs.getString("sex"));
    rc.setPhone(rs.getString("phone"));
    rc.setAddress(rs.getString("address"));
    rc.setClassId(rs.getInt("id_class"));
    return rc;
  }

  public List<RegisterClass> getAll() throws Exception {
    List<RegisterClass> list = new ArrayList<>();
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("select * from registerclass");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      list.add(mapResultSet(rs));
    }
    return list;
  }

  public void add(RegisterClass rc) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement(
        "insert into registerclass(id_user, name, birthday, sex, phone, address, id_class) values (?, ?, ?, ?, ?, ?, ?)");
    ps.setInt(1, rc.getUserId());
    ps.setString(2, rc.getName());
    ps.setDate(3, Date.valueOf(rc.getBirthday()));
    ps.setString(4, rc.getSex());
    ps.setString(5, rc.getPhone());
    ps.setString(6, rc.getAddress());
    ps.setInt(7, rc.getClassId());
    ps.executeUpdate();
  }

  public void detele(int id) throws Exception {
    Connection con = ConnectDB.getConnectDB();
    PreparedStatement ps = con.prepareStatement("delete from registerclass where id_user=?");
    ps.setInt(1, id);
    ps.executeUpdate();
  }
}
