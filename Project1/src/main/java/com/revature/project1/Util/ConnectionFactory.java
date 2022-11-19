package com.revature.project1.Util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Two Design Patterns are needed for our Connection Factory
 * - Design patterns are simply common ways of constructing objects inside of java
 * Singleton Design Pattern
 *      - creational patterns
 *      - A SINGLE instance of that class to be made within the application
 * Factory Design Pattern
 *      - creational
 *      - used to abstract away the creation and instantiation of the class
 *      - churns out instances of connections anywhere in the project
 */
public class ConnectionFactory {
    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); //eager instantiated single
    private Properties properties = new Properties();

    private ConnectionFactory(){
        /* If you want to lazily instantiate a singleton
        if(connectionFactory == null){
            connectionFactory = new ConnectionFactory();

        }
        */
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The purpose is for anywhere in the program to request access to the single connection factory object
     * @return our singleton connection factory object
     */
    public static ConnectionFactory getConnectionFactory(){
        return connectionFactory;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
