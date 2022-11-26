package com.revature.project1.Models;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Objects;


public class Employee {

    private String employeeUsername;

    private boolean employeeRole;

    private String employeeEmail;
    private String employeeName;
    private String employeePassword;

    public Employee() {

    }

    public Employee(String employeeUsername, String employeeEmail, String employeeName, String employeePassword) {
        this.employeeUsername = employeeUsername;
        this.employeeRole = false;
        this.employeeEmail = employeeEmail;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
    }


    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public boolean getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(boolean employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public boolean isValidInput(Employee employee ) {
        return (employeeUsername!= null) & (employeePassword != null);}

    public boolean isBlank(Employee employee) {
        return ((employeeUsername.isBlank()) || (employeePassword.isBlank()) || (employeeUsername.isBlank() && employeePassword.isBlank()));

    }


    public boolean isValidUser() {
        return (this.employeeUsername != null) & (this.employeePassword != null);
    }

    @Override
    public String toString() {
        return
                "EmployeeUsername=" + employeeUsername +
                        ", EmployeeName='" + employeeName +
                        ", EmployeeEmail='" + employeeEmail +
                        ", EmployeeRole=" + employeeRole +
                        '}';
    }}