package com.revature.project1.DAO;

import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;
import com.revature.project1.Util.ConnectionFactory;
import com.revature.project1.Util.Interface.Crudable;

import java.sql.*;
import java.util.List;

    public class RequestDAO implements Crudable<Requests>{

        @Override
        public Requests create(Requests newRequest) {

            try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

                String sql = "insert into requests (r_id, r_status, r_amount, r_type, r_requester) values (?, ?, ?, ?, ?)";
                // PreparedStatements prevent SQL injection
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // set the information for the ?
                preparedStatement.setInt(1, newRequest.getRequestID());
                preparedStatement.setString(2, newRequest.getRequestStatus());
                preparedStatement.setInt(3, newRequest.getRequestAmount());
                preparedStatement.setString(4, newRequest.getRequestType());
                preparedStatement.setObject(5, newRequest.getRequestRequester());

                int checkInsert = preparedStatement.executeUpdate();

                if(checkInsert == 0){
                    throw new RuntimeException("Request was not added to database");
                }

                return newRequest;

            } catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public List<Requests> findAll() {
            return null;
        }

        @Override
        public Requests findByRequestID(int requestID) {
            return null;
        }

        @Override
        public Requests findByRequestType(Requests requestType){
            try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "select * from requests where e_username = ? and r_type = ?";
            // PreparedStatements prevent SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // set the information for the ?
            preparedStatement.setInt(1, newRequest.getRequestID());
            preparedStatement.setString(2, newRequest.getRequestStatus());
            preparedStatement.setInt(3, newRequest.getRequestAmount());
            preparedStatement.setString(4, newRequest.getRequestType());
            preparedStatement.setObject(5, newRequest.getRequestRequester());

            int checkInsert = preparedStatement.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException("Request was not added to database");
            }

            return newRequest;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        }

        @Override
        public boolean update(Requests updatedRequest) {
            return false;
        }

        @Override
        public boolean delete(int requestID) {
            return false;
        }

        @Override
        public Employee findByEmail(String employeeEmail){
            return null;
        }

        @Override
        public boolean delete(String employeeEmail){
            return false;
        }



        }

