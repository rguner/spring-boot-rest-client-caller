package com.example.springbootrestclientcaller.controller;

import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import com.example.springbootrestclientcaller.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersonsFromPersonService();
    }

    @GetMapping("/projection")
    public List<PersonNameAgeProjection> getAllPersonsProjection() {
        return personService.getAllPersonsWithProjectionsFromPersonService();
    }
}
