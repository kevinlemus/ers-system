package com.revature.project1;

import com.revature.project1.Controller.EmployeeController;
import com.revature.project1.Controller.RequestController;
import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.DAO.RequestDAO;
import com.revature.project1.Service.EmployeeService;
import com.revature.project1.Service.RequestService;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        RequestDAO requestDAO = new RequestDAO();
        RequestService requestService = new RequestService(requestDAO);
        EmployeeService employeeService = new EmployeeService(employeeDAO);

        Javalin app = Javalin.create().start(8100);

        new EmployeeController(app).employeeEndpoint();
        RequestController requestController = new RequestController(requestService, employeeService);

        requestController.requestEndpoint(app);
    }
}
