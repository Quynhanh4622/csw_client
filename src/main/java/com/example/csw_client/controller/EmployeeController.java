package com.example.csw_client.controller;

import com.example.csw_client.model.Employee;
import com.example.csw_client.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAllUser() {
        List<Employee> lsEmployee = employeeService.findAll();
        if (lsEmployee.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(lsEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Employee> saveNewUser(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.PUT)
    public ResponseEntity<Employee> saveNewEmployee(
            @Param("id") Integer id,
            @RequestBody Employee employee) {
        Employee oldEmployee = employeeService.findById(id);
        oldEmployee.setName(employee.getName());
        oldEmployee.setSalary(employee.getSalary());
        employeeService.saveEmployee(oldEmployee);
        return new ResponseEntity<Employee>(oldEmployee, HttpStatus.OK);
    }

}
