package com.example.employee.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String name;
    private String dob ;
    private String email ;
    private Double salary;
}
