package com.revature.project1.Service;

import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;

import java.util.ArrayList;
import java.util.List;

public class RequestService {

    private Requests sessionRequest = null;

    private Employee sessionEmployee = null;
    private final RequestDAO requestDAO;//RequestDAO object

    private int requestCount = 0;


    public RequestService(RequestDAO requestDAO){
        this.requestDAO = requestDAO;//whenever we want to initiate a request service, we need to provide it with the request DAO.
    }

    public Requests submitRequest(Requests request, Employee employee){
        request.setRequestRequester(employee);
        request.setRequestID(requestCount++);
        return requestDAO.create(request);

    }



   // public Requests submit(Requests request){
        // TODO: IMPLEMENT ME WITH DAO
      //  sessionRequest = null; // employeeDAO.loginCheck(employeeEmail, employeePassword);
   // }

    public void close(){
        sessionRequest = null;
    }

    public Requests getEmployeeRequest(){
        return sessionRequest;
    }



}
