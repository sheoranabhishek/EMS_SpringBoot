package com.pixxelpanda.springrestapi.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class DepartmentResponse {
    private Long id;
    private String deptName;
}
