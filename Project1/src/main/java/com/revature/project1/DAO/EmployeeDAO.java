package com.revature.project1.DAO;

import com.revature.project1.Models.Employee;
import com.revature.project1.Util.ConnectionFactory;
import com.revature.project1.Util.Exceptions.InvalidEmployeeInputException;
import com.revature.project1.Util.Interface.Crudable;

import java.sql.*;
import java.util.List;

public class EmployeeDAO implements Crudable<Employee> {


    @Override
    public Employee create(Employee newEmployee) {

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {


            String sql = "insert into employee (e_username, e_role, e_email, e_name, e_name, e_password) values (?, ?, ?, ?, ?, ?)";
            // PreparedStatements prevent SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // set the information for the ?
            preparedStatement.setString(1, newEmployee.getEmployeeUsername());
            preparedStatement.setBoolean(2, newEmployee.getEmployeeRole());
            preparedStatement.setString(3,newEmployee.getEmployeeEmail());
            preparedStatement.setString(4, newEmployee.getEmployeeName());
            preparedStatement.setString(5, newEmployee.getEmployeePassword());

            int checkInsert = preparedStatement.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException("Employee was not added to database");
            }

            return newEmployee;

        } catch (SQLException e){
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
    public boolean delete(String employeeEmail) {
        return false;
    }

    public Employee loginCheck(String employeeEmail, String employeePassword, boolean employeeRole){

        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

            String sql = "select * from employee where e_email = ? and e_password = ? and e_role = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeEmail);
            preparedStatement.setString(2, employeePassword);
            preparedStatement.setBoolean(3, employeeRole);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new InvalidEmployeeInputException("The entered information for " + employeeEmail + "was incorrect. Please try again");
            }

            Employee employee = new Employee();

            employee.setEmployeeUsername(resultSet.getString("e_username"));
            employee.setEmployeeRole(resultSet.getBoolean("e_role"));
            employee.setEmployeeEmail(resultSet.getString("e_email"));
            employee.setEmployeeName(resultSet.getString("e_name"));
            employee.setEmployeePassword(resultSet.getString("e_password"));

            return employee;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
