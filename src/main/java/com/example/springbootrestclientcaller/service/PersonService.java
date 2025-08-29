package com.example.springbootrestclientcaller.service;

import com.example.springbootrestclientcaller.client.personservice.PersonServiceRestClient;
import com.example.springbootrestclientcaller.model.personservice.Person;
import com.example.springbootrestclientcaller.model.personservice.PersonNameAgeProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonServiceRestClient personServiceRestClient;

    public List<Person> getAllPersonsFromPersonService() {
        long start = System.currentTimeMillis();
        List<Person> persons = personServiceRestClient.getAllPersons();
        log.info("getAllPersons() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), persons.size());
        return persons;
    }


    public List<PersonNameAgeProjection> getAllPersonsWithProjectionsFromPersonService() {
        long start = System.currentTimeMillis();
        List<PersonNameAgeProjection> personNameAgeProjections = personServiceRestClient.getAllPersonsProjection();
        log.info("getAllPersonsProjection() duration: {} ms, returned {} records", (System.currentTimeMillis()- start), personNameAgeProjections.size());
        return personNameAgeProjections;
    }

    public String loadTest() {
        long start = System.currentTimeMillis();
        IntStream.range(0, 1000).forEach(index -> {
            List<Person> persons = personServiceRestClient.getAllPersons();
        });

        String result = String.format("loadTest() duration: %d ms", (System.currentTimeMillis()- start));
        log.info(result);
        return result;
    }
}
