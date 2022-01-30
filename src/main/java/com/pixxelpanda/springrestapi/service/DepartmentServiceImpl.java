package com.pixxelpanda.springrestapi.service;

import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value = "department" , key = "#dept.getId()")
    public Department saveDepartment(Department dept) {
        return dRepository.save(dept);
    }

    @Override
    public Department getDepartmentByName(String deptName)
    {
        return dRepository.getDepartmentByName(deptName);
    }

    @Override
    @Cacheable(value = "department" , key = "#id")
    public Department getDepartmentById(Long id) {
            System.out.println("Fetching from DB");
          return (dRepository.findById(id).get());
    }

    @Override
    @CacheEvict(value = "department" , allEntries = true)
    public void deleteDepartment(Long id)
    {
        dRepository.deleteById(id);
    }

}
