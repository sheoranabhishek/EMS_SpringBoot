package com.pixxelpanda.springrestapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
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

    @NotBlank(message = "Department should not be null.")
    private String department;

    @UpdateTimestamp
    @Column (name = "updated_at")
    private Date updatedAt;

    @CreationTimestamp
    @Column (name = "created_at" , nullable = false , updatable = false)
    private Date createdAt;
}
