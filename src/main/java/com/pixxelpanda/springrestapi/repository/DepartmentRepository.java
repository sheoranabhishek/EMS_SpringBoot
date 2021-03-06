package com.pixxelpanda.springrestapi.repository;

import com.pixxelpanda.springrestapi.model.Department;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("from Department WHERE deptName = :deptName")
    Department getDepartmentByName(String deptName);

}
