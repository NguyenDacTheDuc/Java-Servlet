package model.entity;

public class Class {
  private int id;
  private int idTeacher;
  private int idDepartment;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIdTeacher() {
    return idTeacher;
  }

  public void setIdTeacher(int idTeacher) {
    this.idTeacher = idTeacher;
  }

  public int getIdDepartment() {
    return idDepartment;
  }

  public void setIdDepartment(int idDepartment) {
    this.idDepartment = idDepartment;
  }
}
