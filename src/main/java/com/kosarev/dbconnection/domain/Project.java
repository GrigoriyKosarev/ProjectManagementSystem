package com.kosarev.dbconnection.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Project {

    private int id;
    private String name;
    private String comment;
    private BigDecimal cost;
    private LocalDate createDate;

    public Project(String name, String comment, BigDecimal cost, LocalDate createDate) {
        setName(name);
        setComment(comment);
        setCost(cost);
    }

    public Project(int id, String name, String comment, BigDecimal cost, LocalDate createDate) {
        setId(id);
        setName(name);
        setComment(comment);
        setCost(cost);
        setCreateDate(createDate);
    }

    public Project(int id, String name, String comment, BigDecimal cost) {
        setId(id);
        setName(name);
        setComment(comment);
        setCost(cost);
    }

    public Project() {
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

    public BigDecimal getCost() {
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

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", cost=" + cost +
                ", createDate=" + createDate +
                '}';
    }
}
