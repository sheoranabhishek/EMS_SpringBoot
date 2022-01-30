package com.pixxelpanda.springrestapi.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "FirstName should not be null.")
    private String name;

    private Integer age = 0;
    private String location;

    @Email(message = "Please enter the valid email address")
    @NotBlank(message = "Email should not be null.")
    private String email;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department dept;

}
