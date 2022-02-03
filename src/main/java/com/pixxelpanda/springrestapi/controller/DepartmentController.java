package com.pixxelpanda.springrestapi.controller;
import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.request.DepartmentRequest;
import com.pixxelpanda.springrestapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService dService;

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getDepartments()
    {
        List<Department> list = dService.getAllDepartments();
        System.out.println(list);
        return new ResponseEntity<List<Department>>( list , HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id)
    {
        return new ResponseEntity<Department>(dService.getDepartmentById(id) , HttpStatus.OK);
    }

    @PostMapping("/department")
    public Department saveDepartment(@Valid @RequestBody Department dept)
    {
        System.out.println(dept);
        dept = dService.saveDepartment(dept);
        return dept;
    }

    @PutMapping("/department/{id}")
    public Department updateDepartment( @PathVariable Long id ,  @RequestBody DepartmentRequest dept)
    {
        //find the id of the department
        Department d = new Department();
        d.setId(id);
        d.setDeptName(dept.getDepartmentName());
        d = dService.saveDepartment(d);
        return d;
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Long id )
    {
        dService.deleteDepartment(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
