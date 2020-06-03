package com.kosarev.dbconnection.domain;

import java.time.LocalDate;

public class Projects {

    private int id;
    private String name;
    private String comment;
    private double cost;
    private LocalDate createDate;

    public Projects() {
    }

    public Projects(String name, String comment, double cost, LocalDate createDate) {
        setName(name);
        setComment(comment);
        setCost(cost);
    }

    public Projects(int id, String name, String comment, double cost, LocalDate createDate) {
        setId(id);
        setName(name);
        setComment(comment);
        setCost(cost);
        setCreateDate(createDate);
    }

    public Projects(int id, String name, String comment, double cost) {
        setId(id);
        setName(name);
        setComment(comment);
        setCost(cost);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public double getCost() {
        return cost;
    }

    public LocalDate getCreateDate() {
        return  this.createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", cost=" + cost +
                ", createDate=" + createDate +
                '}';
    }
}
