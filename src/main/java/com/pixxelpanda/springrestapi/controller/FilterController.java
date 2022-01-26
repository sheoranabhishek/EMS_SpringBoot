package com.pixxelpanda.springrestapi.controller;

import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FilterController {

        @Autowired
        private EmployeeService eService;


        @RequestMapping(value = "/employees/filter" , params = {"name" , "location"})
        public ResponseEntity<List<Employee>> getEmployeeByNameOrLocation(@RequestParam String name , @RequestParam String location)
        {
            System.out.println(name + location);
            return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameOrLocation(name , location) , HttpStatus.OK);
        }

        //get employee with the name /employees/filer?name="..."
        @RequestMapping(value = "/employees/filter" , params = {"name"})
        public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name)
        {
            System.out.println("Name filter");
            return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name) , HttpStatus.OK);
        }



        //get employee with the name and location /employees/filer?name="..."&location="..."
        @GetMapping(value = "/employees/filter/{name}/{location}")
        public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation( @PathVariable String name , @PathVariable String location)
        {
            return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name , location) , HttpStatus.OK);
        }

        //get employee with the name matching to keyword /employees/filer?keyword="..."
        @RequestMapping(value = "/employees/filter" , params = {"keyword"})
        public ResponseEntity<List<Employee>> getEmployeeByKeyword(@RequestParam(name = "keyword") String keyword)
        {
            return new ResponseEntity<List<Employee>>(eService.getEmployeeByKeyword(keyword) , HttpStatus.OK);
        }


}
