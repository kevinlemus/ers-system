package com.revature.project1.Util.Interface;

import com.revature.project1.Models.Employee;
import com.revature.project1.Models.Requests;

import java.util.List;

public interface Crudable<T> {

    // Create
    T create(T newObject);

    // Read
    List<T> findAll();

    Employee findByEmail(String employeeEmail);

    Requests findByRequestID(int requestID);

    // Update
    boolean update(T updatedObject);

    // Delete

    boolean delete(String deletedObject);

    boolean delete(int requestID);

    Requests findByRequestType(Requests requestType);
}