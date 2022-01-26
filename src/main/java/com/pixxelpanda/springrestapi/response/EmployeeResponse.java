package com.pixxelpanda.springrestapi.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class EmployeeResponse {
    private Long id;
    private String EmployeeName;
    private String Department;
    private Integer age;
    private String location;
    private String email;
}
