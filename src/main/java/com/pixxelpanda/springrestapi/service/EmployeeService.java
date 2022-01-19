package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees(int pageNumber , int pageSize);
    Employee saveEmployee(Employee employee);
    Employee getSingleEmployee(Long id);
    void deleteEmployee(Long id);
    Employee updateEmployee(Employee id);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeeByNameAndLocation( String name , String location);
    List<Employee> getEmployeeByKeyword(String name );
}
