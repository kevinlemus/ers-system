package com.revature.project1.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Util.DTO.LoginCreds;
import com.revature.project1.Util.DTO.RequestSubmit;
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
        this.initializeRequestCount();

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

    public int submitRequest(Requests request){
        try{
            Employee requestRequester = this.getSessionEmployee();
            //System.out.println(sessionEmployee);
            int temp = 0;
            if (requestRequester == null) {
                temp = 1; //must be logged in
            } else if (request.getRequestAmount() != 0 && request.getRequestType() != null) {
                request.setRequestStatus("pending");
                request.setRequestRequester(requestRequester.getEmployeeUsername());
                this.initializeRequestCount();
                request.setRequestID(this.requestCount++);
                requestDAO.create(request);
                temp = 2; //success
            } else {
                temp = 3; //must have amount and type
            }
            return temp;
        }  catch (RuntimeException r){
            r.printStackTrace();
        }return 0;}

    private void initializeRequestCount() {
        this.requestCount = this.requestDAO.getRowCount();
        this.requestCount++;
        //System.out.println(this.requestCount);
    }

    public List<Requests> getPendingRequests (){
        return requestDAO.getPendingRequests();
    }
    public void logout(){
        sessionEmployee = null;
    }

    public List<Requests> viewPreviousRequests(Employee employee){
        return requestDAO.viewPreviousRequests(employee);
    }

    public List<Requests> viewRequestByStatus(Employee employee, Requests request){
        return requestDAO.viewRequestsByStatus(employee, request);
    }

    public boolean isManager() {
        if (sessionEmployee == null) return false;
        return !sessionEmployee.getEmployeeRole();
    }

    public boolean isNotManager() {
        if (this.sessionEmployee == null) return true;
        return !this.sessionEmployee.getEmployeeRole();
    }

    public RequestSubmit updateRequest (RequestSubmit update){
        return this.requestDAO.updateRequest(update);
    }

    public void removeEmployee(String employeeUsername){

    }

    public List<Employee> getAllEmployee() {
        return null;
    }
    }