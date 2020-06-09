package com.kosarev.dbconnection.domain;

public class Customer {

    private int id;
    private String name;
    private String fullName;

    public Customer(int id, String name, String fullName) {
        setId(id);
        setName(name);
        setFullName(fullName);
    }

    public Customer(String name, String full_name) {
        setName(name);
        setFullName(full_name);
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", full_name='" + fullName + '\'' +
                '}';
    }
}
