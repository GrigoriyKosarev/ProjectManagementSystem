package com.kosarev.dbconnection.repository;

import com.kosarev.dbconnection.domain.Customers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomersDAO extends ConnectorDB {

    private PreparedStatement insertStatement;
    private PreparedStatement editStatement;
    private PreparedStatement deleteStatement;

    public Optional<Customers> getCustomers(int id) {
        initConnection();
        String selectQwery = "SELECT id, name, full_name " +
                            "FROM goit.customers " +
                            "WHERE id = " + id;
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                String name = resultSet.getNString("name");
                String full_name = resultSet.getNString("full_name");
                return Optional.ofNullable(new Customers(id, name, full_name));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.ofNullable(null);
        } finally {
            closeConnection();
        }
        return Optional.ofNullable(null);
    }

    public Optional<Customers> getCustomers(String name) {
        initConnection();
        String selectQwery = "SELECT id, name, full_name " +
                "FROM goit.customers " +
                "WHERE name = '" + name + "'";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                int id = resultSet.getInt("id");
                String full_name = resultSet.getNString("full_name");
                return Optional.ofNullable(new Customers(id, name, full_name));
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return Optional.ofNullable(null);
        } finally {
            closeConnection();
        }
        return Optional.ofNullable(null);
    }

    public List<Customers> getAllCustomers() {
        initConnection();
        List<Customers> customers = new ArrayList<>();
        String selectQwery = "Select id, name, full_name FROM CUSTOMERS";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            while (resultSet.next()) {
                Customers customer = new Customers();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getNString("name"));
                customer.setFullName(resultSet.getNString("full_name"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Result set error. " + e.getMessage());
            return customers;
        } finally {
            closeConnection();
        }
        return customers;
    }

    public void addCustomers(Customers customers) {
        initConnection();
        initPreparedStatements();
        customers.setId((getLastId()+1));
         try {
            insertStatement.setInt(1, customers.getId());
            insertStatement.setString(2, customers.getName());
            insertStatement.setString(3, customers.getFullName());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: addCustomers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void deleteCustomers(Customers customers) {
        initConnection();
        initPreparedStatements();

        try {
            deleteStatement.setInt(1, customers.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: deleteCustomers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void editCustomers(Customers customers, String name, String fullName) {
        initConnection();
        initPreparedStatements();

        try {
            editStatement.setInt(3, customers.getId());
            editStatement.setString(1, name);
            editStatement.setString(2, fullName);
            editStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute qwery error. funtion: editCustomers" +  e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void initPreparedStatements() {
        try {
            insertStatement = connection.prepareStatement("INSERT INTO CUSTOMERS (id, name, full_name) VALUES (?, ?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM CUSTOMERS WHERE id = ?");
            editStatement = connection.prepareStatement("UPDATE CUSTOMERS SET name = ?, full_name = ? WHERE id = ?");
        } catch (SQLException e) {
            System.out.println("init statement error. funtion: initPreparedStatements" +  e.getMessage());
        }
    }

    private int getLastId() {
        String selectQwery = "SELECT id FROM customers ORDER BY id DESC LIMIT 1";
        resultSet = null;
        try {
            resultSet = statement.executeQuery(selectQwery);
            if (resultSet.first()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Result set error. funtion: getLastId" + e.getMessage());
            return 0;
        } finally {
            closeResultSet();
        }
        return 0;
    }

}
