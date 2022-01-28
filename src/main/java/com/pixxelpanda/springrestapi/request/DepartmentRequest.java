package com.pixxelpanda.springrestapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DepartmentRequest {

    @NotBlank(message = "employeeName is mandatory.")
    private String employeeName;
    @NotBlank(message = "Department Name is mandatory.")
    private String departmentName;

    private Integer age = 0;

    @Email(message = "Please enter the valid email address")
    @NotBlank(message = "Email should not be null.")
    private String email;

    @NotBlank(message = "Location should not be null.")
    private String location;

}
