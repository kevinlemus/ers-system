package com.revature.project1.Controller.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import org.eclipse.jetty.server.Request;

import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    //    overloaded method (method with the same name but different parameters)
    public Employee addEmployee(Employee employee){
        return employeeDAO.create(employee);
    }

    public Employee getEmployee(String employeeName){
        return null;
    }

    public void removeEmployee(String employeeUsername){

    }

    public List<Employee> getAllEmployee() {
        return null;
    }

    public Requests makeRequest(Requests request) {
        return null;
    }

    public void login(String employeeEmail, String employeePassword, boolean employeeRole){
        // TODO: IMPLEMENT ME WITH DAO
        sessionEmployee = employeeDAO.loginCheck(employeeEmail, employeePassword, employeeRole);
    }

    public void logout(){
        sessionEmployee = null;
    }

    public Employee getSessionEmployee(){
        return sessionEmployee;
    }


}
