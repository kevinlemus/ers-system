package com.revature.project1;

import com.revature.project1.Controller.EmployeeController;
import com.revature.project1.Controller.RequestController;
import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Service.EmployeeService;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        //EmployeeDAO employeeDAO = new EmployeeDAO();
       // RequestDAO requestDAO = new RequestDAO();
        //RequestService requestService = new RequestService(requestDAO);

        Javalin app = Javalin.create().start(8080);

        EmployeeService employeeService = new EmployeeService(new EmployeeDAO(), new RequestDAO());


        new EmployeeController(app, employeeService).employeeEndpoint();

        new RequestController(app, employeeService).requestEndpoint();
    }
}
