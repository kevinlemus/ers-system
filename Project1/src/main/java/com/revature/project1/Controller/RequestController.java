package com.revature.project1.Controller;

import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Service.EmployeeService;
import com.revature.project1.Service.RequestService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestController {

    RequestService requestService;
    EmployeeService employeeService;

    RequestDAO requestDAO;

    Javalin app;

    public RequestController(RequestService requestService, EmployeeService employeeService) {
        this.requestService = requestService;
        this.employeeService = employeeService;
    }

    public void requestEndpoint(Javalin app) {


        app.post("submit", this::postSubmitRequestHandler);
        /*app.get("request/findByStatus", this::getRequestByStatusHandler);
        app.get("request/allEmployeeRequests", this::getAllRequestsforEmployeeHandler);
        app.get("request/allManagerRequests", this::getAllRequestsforManagerHandler);
        app.post("request/processStatus", this::postProcessRequestStatus);*/

    }

    private void postSubmitRequestHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Requests request = mapper.readValue(context.body(), Requests.class);
        int temp = requestService.submitRequest(request);

        if (temp==1) {
            context.json("You must be signed in to submit a request.");}
        else if (temp==2) {
                requestDAO.create(request);
                context.json("Your request has been submitted.");}
        else if(temp==3){
            context.json("Your request has not been submitted.");
        }






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
        }}
