package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Customer;
import com.kosarev.dbconnection.error.InternalException;
import com.kosarev.dbconnection.repository.CustomerDAO;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public Customer getCustomer(int id) throws InternalException {
        return customerDAO.getCustomer(id).orElseThrow(() -> {throw new RuntimeException("Customer not found by id " + id);});
    }

    public Customer getCustomer(String name) throws InternalException {
        return customerDAO.getCustomer(name).orElseThrow(() -> {throw new RuntimeException("Customer not found by name " + name);});
    }

    public List<Customer> getAllCustomers() throws InternalException {
        return customerDAO.getAllCustomer();
    }

    public void addCustomer(Customer customer) throws InternalException {
        customerDAO.addCustomer(customer);
    }

    public void deleteCustomer(Customer customer) throws InternalException {
        customerDAO.deleteCustomer(customer);
    }

    public void editCustomer(Customer customer, String name, String fullName) throws InternalException {
        customerDAO.editCustomer(customer, name, fullName);
    }

}
