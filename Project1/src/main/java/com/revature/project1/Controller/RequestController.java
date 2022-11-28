package com.revature.project1.Controller;

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


        app.get("request/viewByStatus", this::viewRequestByStatusHandler);
        app.get("request/previousRequests", this::viewPreviousRequestsHandler);
        app.get("request/pending", this::getPendingRequestsHandler);
        app.post("request/updateRequest", this::postUpdateRequestStatusHandler);
        app.post("submit", this::postSubmitRequestHandler);
    }


    private void viewPreviousRequestsHandler(Context context){
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Requests> previousRequests = employeeService.viewPreviousRequests(employee);
            if (previousRequests != null) {
                context.json(previousRequests);
            } else {context.json("We could not retrieve your previous submissions.");}
        }else{context.json("Please sign in to access your information.");}
    }

    private void postUpdateRequestStatusHandler(Context context) throws JsonProcessingException {
        if (this.employeeService.isNotManager()) {
            context.json("You are not authorized to view this page.");
            return;}

        ObjectMapper mapper = new ObjectMapper();
        RequestSubmit update = mapper.readValue(context.body(), RequestSubmit.class);
        if(this.employeeService.updateRequest(update) == null)context.json("This request was not updated.");
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

    private void getPendingRequestsHandler(Context context) {
        if (this.employeeService.isManager()) {
            context.json("You are not authorized to access this information.");
            return;
        }
        List<Requests> pendingRequests = employeeService.getPendingRequests();

        if (pendingRequests == null) {
            context.json("There are no pending requests.");
            return;
        }
        context.json(pendingRequests);
    }

    private void viewRequestByStatusHandler(Context context) {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            Requests request = new Requests();
            List<Requests> previousRequests = employeeService.viewRequestByStatus(employee, request);
            if (previousRequests != null) {
                context.json(previousRequests);
            } else {
                context.json("We could not retrieve your previous submissions.");
            }
        } else {
            context.json("Please sign in to access your information.");
        }
    }



        /*Employee employee = employeeService.getSessionEmployee();
        if(employee == null) {context.json("You must be signed in to submit a request.");}
        ObjectMapper mapper = new ObjectMapper();
        Requests request = mapper.readValue(context.body(), Requests.class);
        if (requestService.submitRequest(request) == null)
            context.json("Your request has not been submitted. Please try again.");
        else context.json("Your request has been submitted.");
    }



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