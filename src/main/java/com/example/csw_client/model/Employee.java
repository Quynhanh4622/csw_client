package com.example.csw_client.model;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "employees", schema = "CSW", catalog = "")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "salary")
    private Double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee user = (Employee) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(salary, user.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary);
    }
}
