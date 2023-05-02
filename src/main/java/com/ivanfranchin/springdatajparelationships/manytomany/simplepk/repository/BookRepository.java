package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.repository;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
