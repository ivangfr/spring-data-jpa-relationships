package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.repository;

import com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
