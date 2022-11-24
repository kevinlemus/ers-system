package com.revature.project1.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Models.UserRole;
import com.revature.project1.Util.DTO.LoginCreds;

import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;//defaults an inactive website user
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    //    overloaded method (method with the same name but different parameters)
    public Employee registerEmployee(Employee employee){
        return employeeDAO.create(employee);
    }

    public void removeEmployee(String employeeUsername){

    }

    public List<Employee> getAllEmployee() {
        return null;
    }

    public Requests makeRequest(Requests request) {
        return null;
    }

    public Employee login(LoginCreds loginCreds){
        if(sessionEmployee != null) return null;//if the session employee isn't null return nothing.
        sessionEmployee = employeeDAO.loginCheck(loginCreds.getEmployeeUsername(), loginCreds.getEmployeePassword());
        return sessionEmployee;
    }

    public void logout(){
        sessionEmployee = null;
    }

    public Employee getSessionEmployee(){
        return sessionEmployee;
    }

    public boolean isNotManager() {
        if(sessionEmployee == null) return true;
        return sessionEmployee.getEmployeeRole() != UserRole.MANAGER;
    }


}
