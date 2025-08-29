package com.example.springbootrestclientcaller.controller;

import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import com.example.springbootrestclientcaller.service.PersonService;
import com.example.springbootrestclientcaller.service.PersonWithDifferentTimeoutService;
import com.example.springbootrestclientcaller.service.PersonWithDifferentTimeoutWithInjectionService;
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
    private final PersonWithDifferentTimeoutService personWithDifferentTimeoutService;
    private final PersonWithDifferentTimeoutWithInjectionService personWithDifferentTimeoutWithInjectionService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersonsFromPersonService();
    }

    @GetMapping("/projection")
    public List<PersonNameAgeProjection> getAllPersonsProjection() {
        return personService.getAllPersonsWithProjectionsFromPersonService();
    }

    @GetMapping("/differenttimeout")
    public List<Person> getAllPersonsWithDifferentTimeout() {
        return personWithDifferentTimeoutService.getAllPersonsFromPersonService();
    }

    @GetMapping("/differenttimeout/projection")
    public List<PersonNameAgeProjection> getAllPersonsProjectionWithDifferentTimeout() {
        return personWithDifferentTimeoutService.getAllPersonsWithProjectionsFromPersonService();
    }

    @GetMapping("/differenttimeoutwithinjection")
    public List<Person> getAllPersonsWithDifferentTimeoutWithInjection() {
        return personWithDifferentTimeoutWithInjectionService.getAllPersonsFromPersonService();
    }

    @GetMapping("/differenttimeoutwithinjection/projection")
    public List<PersonNameAgeProjection> getAllPersonsProjectionWithDifferentTimeoutWithInjection() {
        return personWithDifferentTimeoutWithInjectionService.getAllPersonsWithProjectionsFromPersonService();
    }
}
