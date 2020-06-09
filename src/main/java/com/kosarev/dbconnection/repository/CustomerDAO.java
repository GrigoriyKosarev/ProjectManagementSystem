package com.kosarev.dbconnection.repository;

import com.kosarev.dbconnection.domain.Customer;
import com.kosarev.dbconnection.error.InternalException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class CustomerDAO extends ConnectorDB {

    private PreparedStatement insertStatement;
    private PreparedStatement editStatement;
    private PreparedStatement deleteStatement;

    public Optional<Customer> getCustomer(int id) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }

        String selectQuery = "SELECT id, name, full_name " +
                            "FROM customers " +
                            "WHERE id = " + id;
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            if (resultSet.first()) {
                String name = resultSet.getNString("name");
                String fullName = resultSet.getNString("full_name");
                return Optional.of(new Customer(id, name, fullName));
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return Optional.empty();
        } finally {
            closeConnection();
        }
        return Optional.empty();
    }

    public Optional<Customer> getCustomer(String name) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        String selectQuery = "SELECT id, name, full_name " +
                "FROM customers " +
                "WHERE name = '" + name + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            if (resultSet.first()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getNString("full_name");
                return Optional.of(new Customer(id, name, fullName));
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return Optional.empty();
        } finally {
            closeConnection();
        }
        return Optional.empty();
    }

    public List<Customer> getAllCustomer() throws InternalException {
        List<Customer> customers = new ArrayList<>();
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }

        String selectQuery = "Select id, name, full_name FROM CUSTOMERS";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getNString("name"));
                customer.setFullName(resultSet.getNString("full_name"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            log.error("Result set error. " + e.getMessage());
            return customers;
        } finally {
            closeConnection();
        }
        return customers;
    }

    public void addCustomer(Customer customer) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }

        initPreparedStatements();
         try {
            insertStatement.setString(1, customer.getName());
            insertStatement.setString(2, customer.getFullName());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
             log.error("execute query error. funtion: addCustomers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteCustomer(Customer customer) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();

        try {
            deleteStatement.setInt(1, customer.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute query error. funtion: deleteCustomers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void editCustomer(Customer customer, String name, String fullName) throws InternalException {
        try {
            initConnection();
        } catch (InternalException e){
            throw new InternalException("Connection error.");
        }
        initPreparedStatements();

        try {
            editStatement.setInt(3, customer.getId());
            editStatement.setString(1, name);
            editStatement.setString(2, fullName);
            editStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("execute query error. funtion: editCustomers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void initPreparedStatements() {
        try {
            insertStatement = connection.prepareStatement("INSERT INTO CUSTOMERS (name, full_name) VALUES (?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM CUSTOMERS WHERE id = ?");
            editStatement = connection.prepareStatement("UPDATE CUSTOMERS SET name = ?, full_name = ? WHERE id = ?");
        } catch (SQLException e) {
            log.error("init statement error. funtion: initPreparedStatements" +  e.getMessage());
        }
    }

}
