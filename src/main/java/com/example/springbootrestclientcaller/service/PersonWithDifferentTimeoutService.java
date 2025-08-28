package com.example.springbootrestclientcaller.service;

import com.example.springbootrestclientcaller.client.personservice.PersonServiceRestClient;
import com.example.springbootrestclientcaller.client.personservicedifferenttimeout.PersonServiceDifferentTimeoutRestClient;
import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonWithDifferentTimeoutService {

    @Qualifier("personServiceDifferentTimeoutRestClient")
    private final PersonServiceDifferentTimeoutRestClient personServiceDifferentTimeoutRestClient;

    public List<Person> getAllPersonsFromPersonService() {
        long start = System.currentTimeMillis();
        List<Person> persons = personServiceDifferentTimeoutRestClient.getAllPersons();
        log.info("getAllPersons() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), persons.size());
        return persons;
    }


    public List<PersonNameAgeProjection> getAllPersonsWithProjectionsFromPersonService() {
        long start = System.currentTimeMillis();
        List<PersonNameAgeProjection> personNameAgeProjections = personServiceDifferentTimeoutRestClient.getAllPersonsProjection();
        log.info("getAllPersonsProjection() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), personNameAgeProjections.size());
        return personNameAgeProjections;
    }
}
