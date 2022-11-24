package com.revature.project1.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Objects;


public class Employee {

    private String e_username;
    private String e_email;
    private String e_name;
    private String e_password;
    private UserRole e_role;

    public Employee() {
    }

    public Employee(String e_username, UserRole e_role, String e_email, String e_name, String e_password) {
        this.e_username = e_username;
        this.e_role = e_role;
        this.e_email = e_email;
        this.e_name = e_name;
        this.e_password = e_password;
    }


    public String getEmployeeUsername() {
        return e_username;
    }

    public void setEmployeeUsername(String e_username) {
        this.e_username = e_username;
    }

    public UserRole getEmployeeRole() {
        return e_role;
    }

    public void setEmployeeRole(UserRole e_role) {
        this.e_role = e_role;
    }

    public String getEmployeeEmail() {
        return e_email;
    }

    public void setEmployeeEmail(String e_email) {
        this.e_email = e_email;
    }

    public String getEmployeeName() {
        return e_name;
    }

    public void setEmployeeName(String e_name){
        this.e_name = e_name;
    }

    public String getEmployeePassword() {
        return e_password;
    }

    public void setEmployeePassword(String e_password) {
        this.e_password = e_password;
    }

    public boolean isExistingUsername() {
            return e_username != null && e_password != null;
        }

    public boolean isCorrectPassword(Employee employee) {
        if (employee == null) return false;

        if (employee.getEmployeePassword() == null) return false;

        return this.e_password != null && this.e_password.equals(employee.getEmployeePassword());
    }

    public boolean isValidUser() {
        return (this.e_username != null) & (this.e_password != null);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Username=" + e_username +
                ", EmployeeName='" + e_name +
                ", EmployeeEmail='" + e_email +
                ", EmployeeRole=" + e_role +
                '}';
    }}