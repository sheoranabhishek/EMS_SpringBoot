package com.pixxelpanda.springrestapi.controller;

import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.repository.DepartmentRepository;
import com.pixxelpanda.springrestapi.repository.EmployeeRepository;
import com.pixxelpanda.springrestapi.request.EmployeeRequest;
import com.pixxelpanda.springrestapi.response.EmployeeResponse;
import com.pixxelpanda.springrestapi.service.DepartmentService;
import com.pixxelpanda.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService eService;

    @Autowired
    private DepartmentService dService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
            return new ResponseEntity<List<Employee>>(eService.getEmployees() ,HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee( @PathVariable("id") Long id )
    {
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id) , HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<HttpStatus> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) throws Exception {

        //check if the department exists or not

        Department dept = dService.getDepartmentByName(eRequest.getDepartmentName());
        if( dept == null)
        {
            throw new Exception("The department you specified , doesn't exist.");
        }

        //otherwise , create a employee out of the request
        Employee emp = new Employee();
        emp.setDept(dept);
        emp.setAge(eRequest.getAge());
        emp.setEmail(eRequest.getEmail());
        emp.setLocation(eRequest.getLocation());
        emp.setName(eRequest.getEmployeeName());

        //saving the employee
        emp = eService.saveEmployee(emp);

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id , @RequestBody Employee employee)
    {
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee) , HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployees( @RequestParam("id") Long id)
    {
        eService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
    }

}
