package com.kosarev.dbconnection.service;

import com.kosarev.dbconnection.domain.Customers;
import com.kosarev.dbconnection.repository.CustomersDAO;

import java.util.List;
import java.util.Optional;

public class CustomersService {

    private final CustomersDAO customersDAO = new CustomersDAO();

    public Customers getCustomers(int id) {
        return customersDAO.getCustomers(id).orElseThrow(() -> {throw new RuntimeException("Customer not found by id " + id);});
    }

    public Customers getCustomers(String name) {
        return customersDAO.getCustomers(name).orElseThrow(() -> {throw new RuntimeException("Customer not found by name " + name);});
    }

    public List<Customers> getAllCustomers() {
        return customersDAO.getAllCustomers();
    }

    public void addCustomers(Customers customers) {
        customersDAO.addCustomers(customers);
    }

    public void deleteCustomers(Customers customers) {
        customersDAO.deleteCustomers(customers);
    }

    public void editCustomers(Customers customers, String name, String fullName) {
        customersDAO.editCustomers(customers, name, fullName);
    }

}
