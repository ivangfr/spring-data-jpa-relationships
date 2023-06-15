package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest;

import com.ivanfranchin.springdatajparelationships.MyContainers;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.Person;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.PersonDetail;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.repository.PersonRepository;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.CreatePersonDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.CreatePersonRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.PersonResponse;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.UpdatePersonDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto.UpdatePersonRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ImportTestcontainers(MyContainers.class)
class PersonDetailControllerTest implements MyContainers {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
    }

    @Test
    void testGetPerson() {
        Person person = personRepository.save(getDefaultPerson());

        String url = String.format(API_PERSONS_PERSON_ID_URL, person.getId());
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.getForEntity(url, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(person.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(person.getName());
        assertThat(responseEntity.getBody().personDetail()).isNull();
    }

    @Test
    void testCreatePerson() {
        CreatePersonRequest createPersonRequest = new CreatePersonRequest("Ivan Franchin");
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.postForEntity(
                API_PERSONS_URL, createPersonRequest, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createPersonRequest.getName());
        assertThat(responseEntity.getBody().personDetail()).isNull();

        Optional<Person> personOptional = personRepository.findById(responseEntity.getBody().id());
        assertThat(personOptional.isPresent()).isTrue();
        personOptional.ifPresent(p -> assertThat(p.getName()).isEqualTo(createPersonRequest.getName()));
    }

    @Test
    void testUpdatePerson() {
        Person person = personRepository.save(getDefaultPerson());
        UpdatePersonRequest updatePersonRequest = new UpdatePersonRequest("Ivan Franchin 2");

        HttpEntity<UpdatePersonRequest> requestUpdate = new HttpEntity<>(updatePersonRequest);
        String url = String.format(API_PERSONS_PERSON_ID_URL, person.getId());
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, requestUpdate, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(updatePersonRequest.getName());

        Optional<Person> personOptional = personRepository.findById(person.getId());
        assertThat(personOptional.isPresent()).isTrue();
        personOptional.ifPresent(p -> assertThat(p.getName()).isEqualTo(updatePersonRequest.getName()));
    }

    @Test
    void testDeletePerson() {
        Person person = personRepository.save(getDefaultPerson());

        String url = String.format(API_PERSONS_PERSON_ID_URL, person.getId());
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(person.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(person.getName());
        assertThat(responseEntity.getBody().personDetail()).isNull();

        Optional<Person> personOptional = personRepository.findById(person.getId());
        assertThat(personOptional.isPresent()).isFalse();
    }

    @Test
    void testAddPersonDetail() {
        Person person = personRepository.save(getDefaultPerson());
        CreatePersonDetailRequest createPersonDetailRequest = new CreatePersonDetailRequest("More information about the person");

        String url = String.format(API_PERSONS_PERSON_ID_PERSON_DETAILS_URL, person.getId());
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.postForEntity(
                url, createPersonDetailRequest, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().personDetail()).isNotNull();
        assertThat(responseEntity.getBody().personDetail().id()).isEqualTo(person.getId());
        assertThat(responseEntity.getBody().personDetail().description())
                .isEqualTo(createPersonDetailRequest.getDescription());

        Optional<Person> personOptional = personRepository.findById(responseEntity.getBody().id());
        assertThat(personOptional.isPresent()).isTrue();
        personOptional.ifPresent(p -> {
            assertThat(p.getPersonDetail().getId()).isEqualTo(person.getId());
            assertThat(p.getPersonDetail().getDescription()).isEqualTo(createPersonDetailRequest.getDescription());
        });
    }

    @Test
    void testUpdatePersonDetail() {
        Person person = getDefaultPerson();
        person.addPersonDetail(getDefaultPersonDetail());
        person = personRepository.save(person);

        UpdatePersonDetailRequest updatePersonDetailRequest = new UpdatePersonDetailRequest("New information about the person");

        HttpEntity<UpdatePersonDetailRequest> requestUpdate = new HttpEntity<>(updatePersonDetailRequest);
        String url = String.format(API_PERSONS_PERSON_ID_PERSON_DETAILS_URL, person.getId());
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, requestUpdate, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().personDetail()).isNotNull();
        assertThat(responseEntity.getBody().personDetail().description())
                .isEqualTo(updatePersonDetailRequest.getDescription());

        Optional<Person> personOptional = personRepository.findById(person.getId());
        assertThat(personOptional.isPresent()).isTrue();
        personOptional.ifPresent(p -> {
            assertThat(p.getPersonDetail()).isNotNull();
            assertThat(p.getPersonDetail().getDescription()).isEqualTo(updatePersonDetailRequest.getDescription());
        });
    }

    @Disabled
    // Hibernate doesn't allow to delete the person-details
    @Test
    void testDeletePersonDetail() {
        Person person = getDefaultPerson();
        PersonDetail personDetail = getDefaultPersonDetail();
        person.addPersonDetail(personDetail);
        person = personRepository.save(person);

        String url = String.format(API_PERSONS_PERSON_ID_PERSON_DETAILS_URL, person.getId());
        ResponseEntity<PersonResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, PersonResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().personDetail()).isNull();

        Optional<Person> personOptional = personRepository.findById(person.getId());
        assertThat(personOptional.isPresent()).isTrue();
        personOptional.ifPresent(p -> assertThat(p.getPersonDetail()).isNull());
    }

    private Person getDefaultPerson() {
        Person person = new Person();
        person.setName("Ivan Franchin");
        return person;
    }

    private PersonDetail getDefaultPersonDetail() {
        PersonDetail personDetail = new PersonDetail();
        personDetail.setDescription("More information about the person");
        return personDetail;
    }

    private static final String API_PERSONS_URL = "/api/persons";
    private static final String API_PERSONS_PERSON_ID_URL = "/api/persons/%s";
    private static final String API_PERSONS_PERSON_ID_PERSON_DETAILS_URL = "/api/persons/%s/person-details";

}