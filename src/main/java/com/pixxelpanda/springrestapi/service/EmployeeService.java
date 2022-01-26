package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Employee;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();
    List<Employee> getEmployees(Optional<Integer> pageNumber , Optional<Integer> pageSize);
    Employee saveEmployee(Employee employee);
    Employee getSingleEmployee(Long id);
    void deleteEmployee(Long id);
    Employee updateEmployee(Employee id);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeeByNameAndLocation( String name , String location);
    List<Employee> getEmployeeByNameOrLocation( String name , String location);
    List<Employee> getEmployeeByKeyword(String name );
}
