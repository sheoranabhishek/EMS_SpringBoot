package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.response.EmployeeResponse;
import org.apache.commons.csv.CSVRecord;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee saveEmployee(Employee employee);
    Employee getSingleEmployee(Long id);
    void deleteEmployee(Long id);
    Employee updateEmployee(Employee id);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeeByNameAndLocation( String name , String location);
    List<Employee> getEmployeeByNameOrLocation( String name , String location);
    List<Employee> getEmployeeByKeyword(String name );
    List<Employee> getEmployeesByDept(String dept);
    void saveCSVtoDB(Iterable<CSVRecord> cv);
}
