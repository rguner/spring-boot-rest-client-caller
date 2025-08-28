package com.example.springbootrestclientcaller.client.personservicedifferenttimeout;

import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(url = "/api/persons", accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface PersonServiceDifferentTimeoutRestClient {

    @GetExchange
    List<Person> getAllPersons();

    @GetExchange("/projection")
    List<PersonNameAgeProjection> getAllPersonsProjection();
}