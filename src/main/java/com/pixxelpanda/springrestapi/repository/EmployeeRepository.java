package com.pixxelpanda.springrestapi.repository;

import com.pixxelpanda.springrestapi.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findByName(String name);
    @Query("FROM Employee WHERE name= :name OR location= :location")
    List<Employee> findByNameOrLocation(String name , String location);
    //for query of name and location
    List<Employee> findByNameAndLocation( String name , String location);
    //select * from table where name LIKE "%ram%"
    List<Employee> findByNameContaining(String keyword , Sort sort);

    @Query("FROM Employee where dept_id = :id")
    List<Employee> findByDepartment(Long id);

}
