package com.revature.project1.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Service.EmployeeService;
import com.revature.project1.Util.DTO.LoginCreds;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.lang.Object;
import java.sql.SQLOutput;

public class EmployeeController {
    EmployeeService employeeService;
    Javalin app;
    public EmployeeController(Javalin app, EmployeeService employeeService){
        this.employeeService = employeeService;
        this.app = app;
    }
    public void employeeEndpoint(){

        app.post("register",this::postRegisterEmployeeHandler);
        //app.get("allEmployees", this::getAllEmployeeHandler);
        //app.get("employee/{employeeUsername}",this::getSpecificEmployeeHandler);
        // app.post("employee/request", this::getPostRequestHandler);
        app.post("login", this::loginHandler);
        app.delete("logout", this::logoutHandler);


    }
    private void postRegisterEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(), Employee.class);
        if(employee.isBlank(employee)){context.json("This username is invalid. Please try again.");}
        //if(employeeService.registerEmployee(employee).isExistingEmployee(employee)) context.json("This username has already been taken. Please try again with a different username.");
        else if (employeeService.registerEmployee(employee)==null) {context.json("This username has already been registered! Please try signing in with a different username.");
        } else context.json("You have successfully been registered!");
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        if(employeeService.login(loginCreds)== null)context.json("The information you provided was incorrect. Please try again.");//
        else context.json("You have successfully logged in!");

    }

    private void logoutHandler(Context context){
        Employee sessionEmployee = employeeService.getSessionEmployee();
        if (sessionEmployee == null) {
            context.json("Must be signed in to log out.");
            return;}
        String employeeUsername = sessionEmployee.getEmployeeUsername();
        employeeService.logout();
        context.json(employeeUsername + " has logged out");
        return;
    }


}