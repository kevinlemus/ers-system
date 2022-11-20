package com.revature.project1.Models;

import java.util.Objects;

public class Employee {

    private String e_username;
    private boolean e_role;
    private String e_email;
    private String e_name;
    private String e_password;

    public Employee() {
    }

    public Employee(String e_username, boolean e_role, String e_email, String e_name, String e_password) {
        this.e_username = e_username;
        this.e_role = e_role;
        this.e_email = e_email;
        this.e_name = e_name;
        this.e_password = e_password;
    }


    public String getEmployeeUsername() {
        return e_username;
    }

    public void setEmployeeUsername(String e_username) { this.e_username = e_username;
    }

    public boolean getEmployeeRole() {
        return e_role;
    }

    public void setEmployeeRole(boolean e_role) {
        this.e_role = e_role;
    }

    public String getEmployeeEmail() {
        return e_email;
    }

    public void setEmployeeEmail(String e_email) {
        this.e_email = e_email;
    }

    public String getEmployeeName() { return e_name; }

    public void setEmployeeName(String e_name){ this.e_name = e_name; }

    public String getEmployeePassword() {
        return e_password;
    }

    public void setPassword(String e_password) {
        this.e_password = e_password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Username=" + e_username +
                ", EmployeeName='" + e_name +
                ", EmployeeEmail='" + e_email +
                ", EmployeeRole='" + e_role + '\'' +
                ", EmployeeRole=" + e_role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return e_username == employee.e_username && Object.equals(e_email, employee, employee.e_email) && Boolean.equals(employee.e_role, e_role) == null && Objects.equals(e_name, employee.e_name) && Objects.equals(e_password, employee.e_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e_username, e_name, e_email, e_role, e_password);
    }

}
