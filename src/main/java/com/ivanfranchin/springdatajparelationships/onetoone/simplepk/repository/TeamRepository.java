package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.repository;

import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
