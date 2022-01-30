package com.pixxelpanda.springrestapi.controller;

import com.pixxelpanda.springrestapi.model.Employee;
import com.pixxelpanda.springrestapi.response.EmployeeResponse;
import com.pixxelpanda.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private EmployeeService eService;

    @GetMapping("/")
    public ModelAndView getHome( )
    {
        List<Employee> list =  eService.getEmployees();
        ModelAndView mav = new ModelAndView("employees");
        mav.addObject("emps" , list);
        return mav;
    }

}
