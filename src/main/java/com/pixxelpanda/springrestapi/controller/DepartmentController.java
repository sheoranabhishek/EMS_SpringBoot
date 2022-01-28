package com.pixxelpanda.springrestapi.controller;
import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.response.DepartmentResponse;
import com.pixxelpanda.springrestapi.service.DepartmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService dService;

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentResponse>> getDepartments()
    {
        List<Department> list = dService.getAllDepartments();
        List<DepartmentResponse> dResponse = new ArrayList<>();

        list.forEach( d->{
            DepartmentResponse dept = new DepartmentResponse();
            dept.setDeptName(d.getDeptName());
            dept.setId(d.getId());
            dResponse.add(dept);
        });

        return new ResponseEntity<List<DepartmentResponse>>( dResponse , HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department dept)
    {
        return new ResponseEntity<Department>( dService.saveDepartment(dept) , HttpStatus.CREATED);
    }
//
//    @PutMapping("/department/{id}")
//    public ResponseEntity<DepartmentResponse> updateDepartment( @RequestParam Long id ,  @RequestBody DepartmentRequest dept)
//    {
//        //find the id of the department
//        Department d = dService.getDepartmentById(id);
//        if( d == null)
//        {
//            throw new RuntimeException("The department with this id" + id +  "doesn't exist.");
//        }
//
//        //found the department , now , we will update the details.
//        Department d =
//
//    }

}
