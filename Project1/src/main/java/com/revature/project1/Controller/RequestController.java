package com.revature.project1.Controller;

import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Service.EmployeeService;
import com.revature.project1.Util.DTO.RequestSubmit;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RequestController {

    EmployeeService employeeService;

    Javalin app;

    public RequestController(Javalin app, EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.app = app;
    }

    public void requestEndpoint() {


        //app.get("request/findByStatus", this::getRequestByStatusHandler);
        app.get("request/allEmployeeRequests", this::viewPreviousRequestsHandler);
        //app.get("request/allManagerRequests", this::getAllRequestsforManagerHandler);
        app.post("request/processStatus", this::postProcessRequestStatusHandler);
        app.post("submit", this::postSubmitRequestHandler);
    }


    private void viewPreviousRequestsHandler(Context context){
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Requests> previousRequests = employeeService.viewPreviousTickets(employee);
            if (previousRequests != null) {
                context.json(previousRequests);
            } else {context.json("We could not retrieve you previous submissions.");}
        }else{context.json("Please sign in to access your information.");}
    }

    private void postProcessRequestStatusHandler(Context context) throws JsonProcessingException {
        if (this.employeeService.isNotManager()) {
            context.json("You are not authorized to view this page.");
            return;}

        ObjectMapper mapper = new ObjectMapper();
        RequestSubmit update = mapper.readValue(context.body(), RequestSubmit.class);
        if(this.employeeService.processRequestUpdate(update) == null)context.json("This request was not updated.");
        else{context.json("This request has been updated.");}}

    private void postSubmitRequestHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Requests request = mapper.readValue(context.body(), Requests.class);
        int temp = employeeService.submitRequest(request);
        //RequestDAO requestDAO = new RequestDAO();

        if (temp==1) {
            context.json("You must be signed in to submit a request.");}
        else if (temp==2) {
            //      requestDAO.create(request);
            context.json("Your request has been submitted.");}
        else if(temp==3){
            context.json("Your request has not been submitted.");
        }}

    /*private void getPendingRequestsHandler(Context context){
        List<Requests> pendingRequests = employeeService.getPendingRequests;
        }*/


        /*Employee employee = employeeService.getSessionEmployee();
        if(employee == null) {context.json("You must be signed in to submit a request.");}

        ObjectMapper mapper = new ObjectMapper();
        Requests request = mapper.readValue(context.body(), Requests.class);
        if (requestService.submitRequest(request) == null)
            context.json("Your request has not been submitted. Please try again.");
        else context.json("Your request has been submitted.");
    }

    private void getRequestByStatusHandler(Context context) throws JsonProcessingException {
        // if (requestService.isNotAManager()) {
        //  context.json("You are not authorized to view this page.");
        //    return;
        /*Employee sessionEmployee = employeeService.getSessionEmployee();
        Requests request = new Requests();
        if (sessionEmployee == null) {context.json("Please sign in to access your information.");
            return;}

        List<Requests> requests = requestService.findByStatus(sessionEmployee, request);

        if (request == null) {context.json("You have not submitted any tickets.");
            return;}*/



 /*       context.json(request);
    }

    private void getAllRequestsforEmployeeHandler(Context context) {
    }
    private void getAllRequestsforManagerHandler(Context context) {
        ;
    }

    private void postProcessRequestStatus(Context context) {
     ;
    }*/
        }
