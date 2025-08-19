package com.example.springbootrestclientcaller.model.personservice;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String name;
    private int age;
    private String lastName;
    private String employeeId;
    private String address;
    private String telephone;

}
