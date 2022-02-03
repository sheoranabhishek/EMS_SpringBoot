package com.pixxelpanda.springrestapi;
import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.repository.EmployeeRepository;
import com.pixxelpanda.springrestapi.repository.EmployeeRepository;
import com.pixxelpanda.springrestapi.service.EmployeeServiceImpl;
import com.pixxelpanda.springrestapi.service.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {EmployeeServiceTest.class})
public class EmployeeServiceTest {

    public Employee e1;
    public Employee e2;

    @InjectMocks
    public EmployeeServiceImpl eService;

    @Mock
    public EmployeeRepository eRepo;

    @Before
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
        e1 = Employee.builder()
                .age(22)
                .name("Abhishek")
                .email("abhisheksheoran2000@gmail.com")
                .id(1L)
                .location("Sonipat")
                .dept(new Department(1L , "Malls")).build();
        e2 = Employee.builder()
                .age(24)
                .name("Kartik")
                .email("kartikvasudev@gmail.com")
                .id(2L)
                .location("Bareily")
                .dept(new Department(2L , "Recharges")).build();

    }

    @Test
    public void getAllEmployees()
    {
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(e1 , e2));

        Mockito.when(eRepo.findAll()).thenReturn(employeeList);
        assertEquals(2 , eService.getEmployees().size() );
        assertEquals("Abhishek" , eService.getEmployees().get(0).getName()) ;
    }

    @Test
    public void saveEmployee()
    {
        Mockito.when(eRepo.save(e1)).thenReturn(e1);
        assertEquals("Abhishek" , eService.saveEmployee(e1).getName());
    }

    @Test
    public void deleteEmployee()
    {
        eService.deleteEmployee(1L);
        Mockito.verify(eRepo , Mockito.times(1)).deleteById(1L);
    }
}
