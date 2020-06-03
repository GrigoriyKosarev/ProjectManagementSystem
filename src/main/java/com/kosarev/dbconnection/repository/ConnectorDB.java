package com.kosarev.dbconnection.repository;

import java.sql.*;

public class ConnectorDB {

    protected static final String LOGIN = "root";
    protected static final String PASSWORD  = "root";
    protected static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/goit?useUnicode=true&serverTimezone=UTC&useSSL=false";
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    protected void initConnection() {
        try {
            connection = null;
            statement = null;
            resultSet = null;
            connection = DriverManager.getConnection(CONNECTION_STRING, LOGIN, PASSWORD);
            statement = connection.createStatement();
        }
        catch (SQLException e){
            System.out.println("Connection error. " + e.getMessage());
        }
    }

    protected void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("resultSet error. " + e.getMessage());
            }
        }
    }

}
