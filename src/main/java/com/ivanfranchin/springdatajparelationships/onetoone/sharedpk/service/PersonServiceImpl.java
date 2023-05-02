package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.service;

import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.exception.PersonNotFoundException;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.Person;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person validateAndGetPerson(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }
}
