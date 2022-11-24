package com.revature.project1.DAO;

import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Models.UserRole;
import com.revature.project1.Util.ConnectionFactory;
import com.revature.project1.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.project1.Util.Interface.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAO implements Crudable<Employee> {


    @Override
    public Employee create(Employee newEmployee) {//function for registering an employee

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            //string "sql" will be used as a function to insert the wanted information into your database.
            String sql = "insert into employee (e_username, e_email, e_name, e_password) values (?, ?, ?, ?)";
            // PreparedStatements prevent SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(sql);//the preparedStatement will be the information input into the function above.

            // set the information for the ?
            preparedStatement.setString(1, newEmployee.getEmployeeUsername());
            //preparedStatement.setString(2, newEmployee.getEmployeeRole());
            preparedStatement.setString(2, newEmployee.getEmployeeEmail());
            preparedStatement.setString(3, newEmployee.getEmployeeName());
            preparedStatement.setString(4, newEmployee.getEmployeePassword());

            int checkInsert = preparedStatement.executeUpdate();//this is counting the updates done. sql table will not properly update if the key, username, already exists.

            if (checkInsert == 0) {//checkInsert equaling 0 means no updates were made
                throw new RuntimeException("Employee was not created");//if no updates were made then no employee was created.
            }

            return newEmployee;//if an update was made, then the given information was successfully inserted(added/registered) into the sql database

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findByEmail(String employeeEmail) {
        return null;
    }

    @Override
    public boolean update(Employee updatedEmployee) {
        return false;
    }

    @Override
    public boolean delete(String deletedObject) {
        return false;
    }

    @Override
    public Requests findByRequestID(int requestID) {
        return null;
    }

    @Override
    public boolean delete(int requestID) {
        return false;
    }

    public Employee loginCheck(String employeeUsername, String employeePassword) {

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "select * from employee where e_username = ? and e_password = ?";//statement we will apply to our sql table
            PreparedStatement preparedStatement = connection.prepareStatement(sql);//preparedStatement is being activated with the sql string above.

            preparedStatement.setString(1, employeeUsername);//this username will be used for the first question mark to execute the command
            preparedStatement.setString(2, employeePassword);//this password will be used for the second question mark to execute the command

            ResultSet resultSet = preparedStatement.executeQuery();//these are the results of what is brought back with the strings above put into the sql statement

            if (!resultSet.next()) {
                throw new InvalidEmployeeInputException("The entered information was incorrect. Please try again");//nothing was retrieved with the given inputs, the loginCheck will tell you to try again.
            }


            Employee employee = new Employee();

            employee.setEmployeeUsername(resultSet.getString("e_username"));
            employee.setEmployeeRole(UserRole.valueOf(resultSet.getString("e_role")));
            //employee.setEmployeeEmail(resultSet.getString("e_email"));
            //employee.setEmployeeName(resultSet.getString("e_name"));
            employee.setEmployeePassword(resultSet.getString("e_password"));

            return employee;// a new session will begin with the information of the username and password that was inputted.

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}




