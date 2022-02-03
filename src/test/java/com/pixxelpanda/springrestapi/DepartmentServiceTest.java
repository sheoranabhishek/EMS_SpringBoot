package com.pixxelpanda.springrestapi;
import com.pixxelpanda.springrestapi.model.Department;
import com.pixxelpanda.springrestapi.repository.DepartmentRepository;
import com.pixxelpanda.springrestapi.service.DepartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepartmentServiceTest.class})
public class DepartmentServiceTest {

    public Department d1;
    public Department d2;

    @InjectMocks
    public DepartmentServiceImpl dService;

    @Mock
    public DepartmentRepository dRepo;

    @Before
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
        d1 = new Department(1L , "Malls");
        d2 = new Department(2L , "Recharges");
    }

    @Test
    public void getAllDepartments()
    {
        List<Department> departmentList = new ArrayList<>(Arrays.asList(d1 , d2));

        Mockito.when(dRepo.findAll()).thenReturn(departmentList);
        assertEquals(2 , dService.getAllDepartments().size() );
        assertEquals("Malls" , dService.getAllDepartments().get(0).getDeptName()) ;

    }

    @Test
    public void getDepartmentById()
    {
        Mockito.when(dRepo.findById(1L)).thenReturn(Optional.ofNullable(d1));
        assertEquals("Malls" , dService.getDepartmentById(1L).getDeptName());
    }

    @Test
    public void saveDepartment()
    {
        Mockito.when(dRepo.save(d1)).thenReturn(d1);
        assertEquals("Malls" , dService.saveDepartment(d1).getDeptName());
    }

    @Test
    public void getDepartmentByName()
    {
        Mockito.when(dRepo.getDepartmentByName("Malls")).thenReturn(d1);
        assertEquals(1L , dService.getDepartmentByName("Malls").getId());
    }

    @Test
    public void deleteDepartment()
    {
        dService.deleteDepartment(1L);
        Mockito.verify(dRepo , Mockito.times(1)).deleteById(1L);
    }
}
