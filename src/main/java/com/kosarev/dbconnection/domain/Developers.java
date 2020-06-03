package com.kosarev.dbconnection.domain;

public class Developers {

    private int id;
    private String name;
    private int age;
    private Sex  sex;
    private double salary;

    public Developers() {
    }

    public Developers(String name, int age, Sex sex, double salary) {
        setName(name);
        setAge(age);
        setSex(sex);
        setSalary(salary);
    }

    public Developers(int id, String name, int age, Sex sex, double salary) {
        setId(id);
        setName(name);
        setAge(age);
        setSex(sex);
        setSalary(salary);
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

    public double getSalary() {
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

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", salary=" + salary +
                '}';
    }
}
