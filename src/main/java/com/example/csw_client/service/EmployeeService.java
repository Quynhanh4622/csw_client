package com.example.csw_client.service;

import com.example.csw_client.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();
    public void saveEmployee(Employee employee);
    public Employee findById(Integer id);
    public void AddEmployees(Integer id);
    public void UpdateEmployee(Integer id);

}
