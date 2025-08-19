package com.example.springbootrestclientcaller.client.personservice;

import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/api/persons", accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface PersonServiceRestClient {

    @GetExchange
    List<Person> getAllPersons();

    @GetExchange("/projection")
    List<PersonNameAgeProjection> getAllPersonsProjection();
}