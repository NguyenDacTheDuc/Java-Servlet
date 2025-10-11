package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
  private static String url = "jdbc:mysql://localhost:3306/studentmanagement?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
  private static String user = "root";
  private static String pass = "12345678";

  static public Connection getConnectDB() throws Exception, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(url, user, pass);
  }

  public static void main(String[] args) {
    try {
      Connection conn = ConnectDB.getConnectDB();
      if (conn != null) {
        System.out.println("Connected to the database successfully!");
      } else {
        System.out.println("Failed to connect to the database.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
