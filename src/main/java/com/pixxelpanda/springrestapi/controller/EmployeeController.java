package com.pixxelpanda.springrestapi.controller;

import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService eService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam int pNum , int pSize){
        return new ResponseEntity<List<Employee>>(eService.getEmployees(pNum , pSize) ,HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee( @PathVariable("id") Long id )
    {
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id) , HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
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

    @RequestMapping(value = "/employees/filter" , params = {"name"})
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name)
    {
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name) , HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/filter" , params = {"name" , "location"})
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation( @RequestParam String name , String location)
    {
        return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name , location) , HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/filter" , params = {"keyword"})
    public ResponseEntity<List<Employee>> getEmployeeByKeyword(@RequestParam String keyword)
    {
        return new ResponseEntity<List<Employee>>(eService.getEmployeeByKeyword(keyword) , HttpStatus.OK);
    }


}
