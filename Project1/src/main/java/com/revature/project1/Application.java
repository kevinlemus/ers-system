package com.revature.project1;

import com.revature.project1.Controller.EmployeeController;
import com.revature.project1.Controller.RequestController;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        new EmployeeController(app).employeeEndpoint();
        RequestController requestController = new RequestController();

        requestController.requestEndpoint(app);
    }
}
