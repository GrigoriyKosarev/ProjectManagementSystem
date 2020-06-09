package com.kosarev.dbconnection.domain;

import java.math.BigDecimal;

public class Developer {

    private int id;
    private String name;
    private int age;
    private Sex  sex;
    private BigDecimal salary;

    public Developer(String name, int age, Sex sex, BigDecimal salary) {
        setName(name);
        setAge(age);
        setSex(sex);
        setSalary(salary);
    }

    public Developer(int id, String name, int age, Sex sex, BigDecimal salary) {
        setId(id);
        setName(name);
        setAge(age);
        setSex(sex);
        setSalary(salary);
    }

    public Developer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", salary=" + salary +
                '}';
    }
}
