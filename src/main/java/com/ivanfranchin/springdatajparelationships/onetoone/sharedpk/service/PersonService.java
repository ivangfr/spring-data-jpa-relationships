package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.service;

import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.Person;

public interface PersonService {

    Person validateAndGetPerson(Long id);

    Person savePerson(Person person);

    void deletePerson(Person person);
}
