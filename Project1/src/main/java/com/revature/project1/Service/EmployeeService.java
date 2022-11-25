package com.revature.project1.Service;

import com.revature.project1.DAO.EmployeeDAO;
import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Util.DTO.LoginCreds;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;//defaults an inactive website user
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    //    overloaded method (method with the same name but different parameters)
    public Employee registerEmployee(Employee newEmployee){
        return employeeDAO.create(newEmployee);
      // List<Employee> employees = employeeDAO.findAll();
       // List<String> usernames = new ArrayList<>();
      //  int temp = 0;

     //   for (int i = 0; i < employees.size(); i++) {
      //      usernames.add(employees.get(i).getEmployeeUsername());
      //  }

    //    if (newEmployee.getEmployeePassword() != null && newEmployee.getEmployeeUsername() != null) { //if password and username filled out

     //       if (usernames.contains(newEmployee.getEmployeeUsername())) { //username already registered
    //            temp = 1;
    //        } else {
   //             employeeDAO.create(newEmployee); //success
   //             temp = 2;
   //         }
    //    }
     //   return temp;
    }

    public boolean isManager(){
        if(sessionEmployee==null) return false;
        return !sessionEmployee.getEmployeeRole();
    }

    public void removeEmployee(String employeeUsername){

    }

    public List<Employee> getAllEmployee() {
        return null;
    }

    public Requests makeRequest(Requests request) {
        return null;
    }

    public Employee login(LoginCreds loginCreds){
        if(sessionEmployee != null) return null;//if the session employee isn't null return nothing.
        sessionEmployee = employeeDAO.loginCheck(loginCreds.getEmployeeUsername(), loginCreds.getEmployeePassword());
        return sessionEmployee;
    }

    public void logout(){
        sessionEmployee = null;
    }

    public Employee getSessionEmployee(){
        return sessionEmployee;
    }



}
