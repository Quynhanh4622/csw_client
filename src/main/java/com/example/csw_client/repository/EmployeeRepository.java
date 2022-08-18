package com.example.csw_client.repository;

import com.example.csw_client.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAllByName(String name);
}
