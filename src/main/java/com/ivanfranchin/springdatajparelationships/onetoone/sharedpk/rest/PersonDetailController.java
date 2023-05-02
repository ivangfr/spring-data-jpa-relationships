package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest;

import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.mapper.PersonMapper;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.Person;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.PersonDetail;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.CreatePersonDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.CreatePersonRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.PersonResponse;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.UpdatePersonDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.UpdatePersonRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonDetailController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("/{personId}")
    public PersonResponse getPerson(@PathVariable Long personId) {
        Person person = personService.validateAndGetPerson(personId);
        return personMapper.toPersonResponse(person);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonResponse createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest) {
        Person person = personMapper.toPerson(createPersonRequest);
        person = personService.savePerson(person);
        return personMapper.toPersonResponse(person);
    }

    @PutMapping("/{personId}")
    public PersonResponse updatePerson(@PathVariable Long personId,
                                       @Valid @RequestBody UpdatePersonRequest updatePersonRequest) {
        Person person = personService.validateAndGetPerson(personId);
        personMapper.updatePersonFromRequest(updatePersonRequest, person);
        person = personService.savePerson(person);
        return personMapper.toPersonResponse(person);
    }

    @DeleteMapping("/{personId}")
    public PersonResponse deletePerson(@PathVariable Long personId) {
        Person person = personService.validateAndGetPerson(personId);
        personService.deletePerson(person);
        return personMapper.toPersonResponse(person);
    }

    @PostMapping("/{personId}/person-details")
    public PersonResponse addPersonDetail(@PathVariable Long personId,
                                          @Valid @RequestBody CreatePersonDetailRequest createPersonDetailRequest) {
        Person person = personService.validateAndGetPerson(personId);
        PersonDetail personDetail = personMapper.toPersonDetail(createPersonDetailRequest);
        person.addPersonDetail(personDetail);
        person = personService.savePerson(person);
        return personMapper.toPersonResponse(person);
    }

    @PutMapping("/{personId}/person-details")
    public PersonResponse updatePersonDetail(@PathVariable Long personId,
                                             @Valid @RequestBody UpdatePersonDetailRequest updatePersonDetailRequest) {
        Person person = personService.validateAndGetPerson(personId);
        PersonDetail personDetail = person.getPersonDetail();
        personMapper.updatePersonDetailFromRequest(updatePersonDetailRequest, personDetail);
        person = personService.savePerson(person);
        return personMapper.toPersonResponse(person);
    }

    // Hibernate doesn't allow to delete the person-details
    @DeleteMapping("/{personId}/person-details")
    public PersonResponse deletePersonDetail(@PathVariable Long personId) {
        Person person = personService.validateAndGetPerson(personId);
        person.removePersonDetail();
        person = personService.savePerson(person);
        return personMapper.toPersonResponse(person);
    }
}
