package com.revature.project1.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Util.DTO.LoginCreds;

public class RequestService {

    private Requests sessionRequest = null;

    private Employee sessionEmployee = null;
    private final RequestDAO requestDAO;//RequestDAO object

    private int requestCount = 0;


    public RequestService(RequestDAO requestDAO){
        this.requestDAO = requestDAO;//whenever we want to initiate a request service, we need to provide it with the request DAO.
    }

    public Employee login(LoginCreds loginCreds) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        if (sessionEmployee != null) return null;//if the session employee isn't null return nothing.
        sessionEmployee = employeeDAO.loginCheck(loginCreds.getEmployeeUsername(), loginCreds.getEmployeePassword());
        return sessionEmployee;
    }
    public int submitRequest(Requests request){
        try {
            ;
            int temp = 0;
            if (this.sessionEmployee == null) {
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

        } catch (RuntimeException r) {
            r.printStackTrace();
            System.out.println("request does not exist");
        }
     return 0;
    }



    /*public List<Requests> findByStatus(Employee employee, Requests request){
        return requestDAO.findByStatus(employee, request);
        /*
        if(requestDAO.findByStatus(request).equals("APPROVED")){
            return requestDAO.isApproved();}
        if(requestDAO.findByStatus().equals("DENIED")){
            return requestDAO.isDenied();}
        else{ return requestDAO.isPending();}*/

    }/*






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



}*/
