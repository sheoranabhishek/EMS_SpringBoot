package com.pixxelpanda.springrestapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DepartmentRequest {
    @NotBlank(message = "Department Name is mandatory.")
    private String departmentName;
}
