package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.repository.EmployeeRepository;
import com.pixxelpanda.springrestapi.response.EmployeeResponse;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static List<Employee> list = new ArrayList<>();

    @Autowired
    private EmployeeRepository eRepository;

    @Autowired
    private DepartmentService dService;

    @Override
    public List<Employee> getEmployees() {
        List<Employee> dbList;
        dbList = (List<Employee>) eRepository.findAll();
        System.out.println("Getting Data from DB" + dbList);
        return dbList;
    }

    @Override
    public Employee saveEmployee(Employee employee)
    {
        return eRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
       Optional<Employee> employee =  eRepository.findById(id);
       if( employee.isPresent())
       {
           return employee.get();
       }
       throw new RuntimeException("Employee is not found for the id " + id);
    }

    @Override
    public void deleteEmployee(Long id) {
        eRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee( Employee e)
    {
        return eRepository.save(e);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return eRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeeByNameAndLocation( String name , String location)
    {
        return eRepository.findByNameAndLocation(name , location);
    }

    @Override
    public List<Employee> getEmployeeByNameOrLocation( String name , String location)
    {
        System.out.println(name + location);
        return eRepository.findByNameOrLocation(name , location);
    }

    @Override
    public List<Employee> getEmployeeByKeyword( String keyword)
    {
        Sort sort = Sort.by(Sort.Direction.DESC , "id");
        return eRepository.findByNameContaining(keyword , sort);
    }

    @Override
    @Transactional
    public void saveCSVtoDB(Iterable<CSVRecord> csvRecords)
    {
        for (CSVRecord csvRecord : csvRecords) {
            String deptName = csvRecord.get("department");
            Department d = dService.getDepartmentByName(deptName);
            if( d == null) {
                throw new RuntimeException("The department name inside CSV , doesn't match any.");
            }
            Employee e = new Employee();
            e.setName(csvRecord.get("name"));
            e.setEmail(csvRecord.get("email"));
            e.setLocation(csvRecord.get("location"));
            e.setDept(d);
            e.setAge(Integer.parseInt(csvRecord.get("age")));
            System.out.println(e.toString());
            eRepository.save(e);
        }
    }

    @Override
    public List<Employee> getEmployeesByDept(String dept)
    {
        //sending the dept id;
        Department d = dService.getDepartmentByName(dept);
        return eRepository.findByDepartment(d.getId());
    }

}
