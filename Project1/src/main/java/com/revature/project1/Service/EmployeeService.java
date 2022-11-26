package com.revature.project1.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Util.DTO.LoginCreds;
import org.eclipse.jetty.http.MetaData;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;//defaults an inactive website user
    private final EmployeeDAO employeeDAO;
    private final RequestDAO requestDAO;

    private int requestCount = 0;


    public EmployeeService(EmployeeDAO employeeDAO, RequestDAO requestDAO){
        this.employeeDAO = employeeDAO;
        this.requestDAO = requestDAO;

    }

    public Employee getSessionEmployee(){
        return sessionEmployee;
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


    public boolean isManager(){
        if(sessionEmployee==null) return false;
        return !sessionEmployee.getEmployeeRole();
    }


    public int submitRequest(Requests request){
        Employee sessionEmployee = this.getSessionEmployee();
        System.out.println(sessionEmployee);
            int temp = 0;
            if (sessionEmployee == null) {
                temp = 1; //must be logged in
            } else if (request.getRequestAmount() != 0 && request.getRequestType() != null) {
                request.setRequestStatus("pending");
                request.setEmployee(sessionEmployee.getEmployeeUsername());
                request.setRequestID(requestCount++);
                requestDAO.create(request);
                temp = 2; //success
            } else {
                temp = 3; //must have amount and type
            }
            return temp;
    }

    public List<Requests> viewPreviousTickets(Employee employee){
        return requestDAO.viewPreviousRequests(employee);
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

