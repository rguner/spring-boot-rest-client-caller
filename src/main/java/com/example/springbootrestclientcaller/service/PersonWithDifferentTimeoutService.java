package com.example.springbootrestclientcaller.service;

import com.example.springbootrestclientcaller.client.personservice.PersonServiceRestClient;
import com.example.springbootrestclientcaller.client.personservicedifferenttimeout.PersonServiceDifferentTimeoutHttpClientConfig;
import com.example.springbootrestclientcaller.client.personservicedifferenttimeout.PersonServiceDifferentTimeoutRestClient;
import com.example.springbootrestclientcaller.config.TimeoutConfig;
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

    //@Qualifier("personServiceDifferentTimeoutRestClient")
    //private final PersonServiceDifferentTimeoutRestClient personServiceDifferentTimeoutRestClient;

    private final PersonServiceDifferentTimeoutHttpClientConfig personServiceDifferentTimeoutHttpClientConfig;

    public List<Person> getAllPersonsFromPersonService() {
        TimeoutConfig timeoutConfig = TimeoutConfig.builder()
                .requestTimeout(5000)
                .responseTimeout(5000)
                .socketTimeout(2000)
                .build();
        PersonServiceDifferentTimeoutRestClient personServiceDifferentTimeoutRestClient =
                personServiceDifferentTimeoutHttpClientConfig.getPersonServiceRestClient(timeoutConfig);
        long start = System.currentTimeMillis();
        List<Person> persons = personServiceDifferentTimeoutRestClient.getAllPersons();
        log.info("getAllPersons() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), persons.size());
        return persons;
    }


    public List<PersonNameAgeProjection> getAllPersonsWithProjectionsFromPersonService() {

        TimeoutConfig timeoutConfig = TimeoutConfig.builder()
                .requestTimeout(6000)
                .responseTimeout(6000)
                .socketTimeout(2000)
                .build();

        PersonServiceDifferentTimeoutRestClient personServiceDifferentTimeoutRestClient =
                personServiceDifferentTimeoutHttpClientConfig.getPersonServiceRestClient(timeoutConfig);
        long start = System.currentTimeMillis();
        List<PersonNameAgeProjection> personNameAgeProjections = personServiceDifferentTimeoutRestClient.getAllPersonsProjection();
        log.info("getAllPersonsProjection() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), personNameAgeProjections.size());
        return personNameAgeProjections;
    }
}
