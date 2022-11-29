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


        app.get("request/personalApproved", this::viewApprovedPersonalRequestsHandler);
        app.get("request/personalDenied", this::viewDeniedPersonalRequestsHandler);
        app.get("request/personalPending", this::viewPendingPersonalRequestsHandler);
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

    private void viewPendingPersonalRequestsHandler(Context context) throws JsonProcessingException {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Requests> personalRequests = employeeService.pendingPersonalRequests(employee);
            if (personalRequests != null) {
                context.json(personalRequests);
            } else {
                context.json("We could not find any pending requests.");
            }
        } else {
            context.json("You must be logged in to view your pending requests");
        }
    }

    private void viewApprovedPersonalRequestsHandler(Context context) {
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Requests> personalRequests = employeeService.approvedPersonalRequests(employee);
            if (personalRequests != null) {
                context.json(personalRequests);
            } else {
                context.json("We could not find any approved requests.");
            }
        } else {
            context.json("You must be logged in to view your approved requests");
        }
    }

    private void viewDeniedPersonalRequestsHandler(Context context){
        if (employeeService.getSessionEmployee() != null) {
            Employee employee = employeeService.getSessionEmployee();
            List<Requests> personalRequests = employeeService.deniedPersonalRequests(employee);
            if (personalRequests != null) {
                context.json(personalRequests);
            } else {
                context.json("We could not find any denied requests.");
            }
        } else {
            context.json("You must be logged in to view your denied requests");
        }
    }
}