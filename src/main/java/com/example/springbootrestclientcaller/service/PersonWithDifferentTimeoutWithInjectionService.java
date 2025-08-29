package com.example.springbootrestclientcaller.service;

import com.example.springbootrestclientcaller.client.personservicedifferenttimeout.PersonServiceDifferentTimeoutHttpClientConfig;
import com.example.springbootrestclientcaller.client.personservicedifferenttimeout.PersonServiceDifferentTimeoutRestClient;
import com.example.springbootrestclientcaller.config.TimeoutConfig;
import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonWithDifferentTimeoutWithInjectionService {

    private final PersonServiceDifferentTimeoutRestClient personServiceDifferentTimeoutRestClientDefault;

    public List<Person> getAllPersonsFromPersonService() {

        long start = System.currentTimeMillis();
        List<Person> persons = personServiceDifferentTimeoutRestClientDefault.getAllPersons();
        log.info("getAllPersons() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), persons.size());
        return persons;
    }


    public List<PersonNameAgeProjection> getAllPersonsWithProjectionsFromPersonService() {

        long start = System.currentTimeMillis();
        List<PersonNameAgeProjection> personNameAgeProjections = personServiceDifferentTimeoutRestClientDefault.getAllPersonsProjection();
        log.info("getAllPersonsProjection() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), personNameAgeProjections.size());
        return personNameAgeProjections;
    }
}
