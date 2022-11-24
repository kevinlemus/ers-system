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

        Javalin app;

        public RequestController(RequestService requestService, EmployeeService employeeService) {
            this.requestService = requestService;
            this.employeeService = employeeService;
        }

        public void requestEndpoint(Javalin app) {


            app.post("submit", this::postSubmitRequestHandler);

        }

        private void postSubmitRequestHandler(Context context) throws JsonProcessingException{
            Employee employee = employeeService.getSessionEmployee();
            ObjectMapper mapper = new ObjectMapper();
            Requests request = mapper.readValue(context.body(), Requests.class);
            if(requestService.submitRequest(request, employee)==null) context.json("Your request has not been submitted. Please try again.");
            else context.json("Your request has been submitted.");
        }



    }
