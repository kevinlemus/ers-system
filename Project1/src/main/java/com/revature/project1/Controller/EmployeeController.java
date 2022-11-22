package com.revature.project1.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Controller.Service.EmployeeService;
import com.revature.project1.Util.DTO.LoginCreds;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {
    EmployeeService employeeService;
    Javalin app;
    public EmployeeController(Javalin app){
        employeeService = new EmployeeService(new EmployeeDAO());
        this.app = app;
    }
    public void employeeEndpoint(){

        app.get("hello", this::helloHandler);
        app.post("register",this::postEmployeeHandler);
        app.get("allEmployees", this::getAllEmployeeHandler);
        app.get("employee/{employeeUsername}",this::getSpecificEmployeeHandler);
        app.post("employee/request", this::getPostRequestHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);
    }

    private void getSpecificEmployeeHandler(Context context) {
        String name = context.pathParam("name");
        Employee customer = employeeService.getEmployee(name);
        context.json(customer);
    }

    private void getPostRequestHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Requests requestID = mapper.readValue(context.body(), Requests.class);
        Requests requestType = employeeService.makeRequest(requestID);
        context.json(requestType);

    }

    private void getAllEmployeeHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployee();
//        similar as context.result, but the content type is json rather than text.
        context.json(allEmployees);
    }

    private void postEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        employeeService.addEmployee(employee);
        context.json(employee);
    }

    public void helloHandler(Context ctx){
        ctx.result("hello world");
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        employeeService.login(loginCreds.getEmployeeEmail(), loginCreds.getEmployeePassword(), loginCreds.getEmployeeRole());
        context.json("Successfully logged in");
    }

    private void logoutHandler(Context context){
        String employeeName = employeeService.getSessionEmployee().getEmployeeName();
        employeeService.logout();
        context.json(employeeName + " has logged out");
    }
}
