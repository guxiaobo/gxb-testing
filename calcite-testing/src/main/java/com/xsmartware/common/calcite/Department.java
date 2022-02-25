package com.xsmartware.common.calcite;

import java.util.List;
import java.util.Objects;

/**
 * Department model.
 */
public class Department {
  public final int deptno;
  public final String name;

  @org.apache.calcite.adapter.java.Array(component = Employee.class)
  public final List<Employee> employees;
  public final Location location;

  public Department(int deptno, String name, List<Employee> employees,
      Location location) {
    this.deptno = deptno;
    this.name = name;
    this.employees = employees;
    this.location = location;
  }

  @Override public String toString() {
    return "Department [deptno: " + deptno + ", name: " + name
        + ", employees: " + employees + ", location: " + location + "]";
  }

  @Override public boolean equals(Object obj) {
    return obj == this
        || obj instanceof Department
        && deptno == ((Department) obj).deptno;
  }

  @Override public int hashCode() {
    return Objects.hash(deptno);
  }
}
