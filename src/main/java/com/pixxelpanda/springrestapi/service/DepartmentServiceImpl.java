package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static List<Department> list = new ArrayList<>();

    @Autowired
    private DepartmentRepository dRepository;

    @Override
    public List<Department> getAllDepartments(){
        return dRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department dept) {
        return dRepository.save(dept);
    }

    @Override
    public Department getDepartmentByName(String deptName)
    {
        return dRepository.getDepartmentByName(deptName);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return dRepository.getById(id);
    }

    @Override
    public void deleteDepartment(Long id)
    {
        dRepository.deleteById(id);
    }

}
