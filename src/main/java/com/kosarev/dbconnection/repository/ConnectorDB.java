package com.kosarev.dbconnection.repository;

import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class ConnectorDB {

    protected static final String LOGIN = "root";
    protected static final String PASSWORD  = "root";
    protected static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/goit?useUnicode=true&serverTimezone=UTC&useSSL=false";
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    protected void initConnection() throws SQLException {
        try {
            connection = null;
            statement = null;
            resultSet = null;
            connection = DriverManager.getConnection(CONNECTION_STRING, LOGIN, PASSWORD);
            statement = connection.createStatement();
        }
        catch (SQLException e){
            log.error("Connection error. " + e.getMessage());
            throw new SQLException();
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
               log.error("resultSet error. " + e.getMessage());
            }
        }
    }

}
