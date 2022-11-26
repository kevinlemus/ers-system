package com.revature.project1.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Util.DTO.LoginCreds;
import org.eclipse.jetty.http.MetaData;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;//defaults an inactive website user
    private final EmployeeDAO employeeDAO;


    private int requestCount = 0;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;

    }

    //    overloaded method (method with the same name but different parameters)
    public Employee registerEmployee(Employee newEmployee){
        return employeeDAO.create(newEmployee);
    }

    public Employee login(LoginCreds loginCreds){
        if(sessionEmployee != null) return null;//if the session employee isn't null return nothing.
        sessionEmployee = employeeDAO.loginCheck(loginCreds.getEmployeeUsername(), loginCreds.getEmployeePassword());
        return sessionEmployee;
    }

    public Employee getSessionEmployee() {
        return getSessionEmployee();
    }
    public boolean isManager(){
        if(sessionEmployee==null) return false;
        return !sessionEmployee.getEmployeeRole();
    }

    public void removeEmployee(String employeeUsername){

    }

    public List<Employee> getAllEmployee() {
        return null;
    }

    public Requests makeRequest(Requests request) {
        return null;
    }



    public void logout(){
        sessionEmployee = null;
    }}

