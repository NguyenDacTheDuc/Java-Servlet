package model.entity;

public class Grades {
  private int idStudent;
  private int idSubject;
  private float grade;

  public int getIdStudent() {
    return idStudent;
  }

  public void setIdStudent(int idStudent) {
    this.idStudent = idStudent;
  }

  public int getIdSubject() {
    return idSubject;
  }

  public void setIdSubject(int idSubject) {
    this.idSubject = idSubject;
  }

  public float getGrade() {
    return grade;
  }

  public void setGrade(float grade) {
    this.grade = grade;
  }
}
