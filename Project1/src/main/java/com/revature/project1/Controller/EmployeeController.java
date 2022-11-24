package com.revature.project1.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Service.EmployeeService;
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
        if(employeeService.registerEmployee(employee).isExistingUsername()) context.json("This username has already been taken. Please try again with a different username.");
        if(employeeService.registerEmployee(employee)==null) context.json("Your username, " + employee.getEmployeeUsername() + ", has not been registered. Please try again with a different e-mail.");
        else context.json("Your username, " + employee.getEmployeeUsername() + ", has been registered!");
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCreds loginCreds = mapper.readValue(context.body(), LoginCreds.class);
        if(employeeService.login(loginCreds)== null)context.json("Something went wrong. Please try logging in again.");//
        else context.json("You have successfully logged in!");
    }

    private void logoutHandler(Context context){
        String employeeUsername = employeeService.getSessionEmployee().getEmployeeUsername();
        employeeService.logout();
        context.json(employeeUsername + " has logged out");
    }


}

//public void helloHandler(Context ctx){
//ctx.result("hello world");
// }

//private void getSpecificEmployeeHandler(Context context) {
//String username = context.pathParam("username");
// Employee employee = employeeService.getSpecificEmployee(username);
//context.json(employee);
// }

// private void getPostRequestHandler(Context context) throws JsonProcessingException {
//ObjectMapper mapper = new ObjectMapper();
// Requests requestID = mapper.readValue(context.body(), Requests.class);
// Requests requestType = employeeService.makeRequest(requestID);
// context.json(requestType);

//}

// private void getAllEmployeeHandler(Context context) {
//List<Employee> allEmployees = employeeService.getAllEmployee();
//        similar as context.result, but the content type is json rather than text.
//   context.json(allEmployees);
//}
