package com.kosarev.dbconnection.domain;

public class Customers {

    private int id;
    private String name;
    private String full_name;

    public Customers(int id, String name, String full_name) {
        setId(id);
        setName(name);
        setFullName(full_name);
    }

    public Customers(String name, String full_name) {
        setName(name);
        setFullName(full_name);
    }

    public Customers() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return full_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.full_name = fullName;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
