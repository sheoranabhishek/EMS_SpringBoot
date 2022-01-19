package com.pixxelpanda.springrestapi.repository;

import com.pixxelpanda.springrestapi.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findByName(String name);

    //for query of name and location
    List<Employee> findByNameAndLocation( String name , String location);

    //select * from table where name LIKE "%ram%"
    List<Employee> findByNameContaining(String keyword , Sort sort);
}
