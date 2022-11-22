package com.revature.project1.Util.Interface;

import java.util.List;

public interface Crudable<T> {

    // Create
    T create(T newObject);

    // Read
    List<T> findAll();
    T findByEmail(String employeeEmail);

    // Update
    boolean update(T updatedObject);

    // Delete
    boolean delete(String EmployeeEmail);

}
