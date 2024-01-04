package com.classroom.assignment.model.request;

public class Student {
  private String id;
  private String name;
  private String department;

  public Student(String id, String name, String department) {
    this.setId(id);
    this.setName(name);
    this.setDepartment(department);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
