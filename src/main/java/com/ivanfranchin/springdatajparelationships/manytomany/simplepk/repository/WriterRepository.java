package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Writer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends CrudRepository<Writer, Long> {
}
