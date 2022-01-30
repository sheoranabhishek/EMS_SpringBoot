package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department saveDepartment(Department department);
    void deleteDepartment(Long id);
    Department getDepartmentByName(String departmentName);
    Department getDepartmentById(Long id);
}
