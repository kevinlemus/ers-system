package com.revature.project1.Util.DTO;

public class LoginCreds {

    private String employeeEmail;
    private String employeePassword;
    private boolean employeeRole;

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setCustomerName(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setPassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public boolean getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(boolean employeeRole) {
        this.employeeRole = employeeRole;
    }

}
