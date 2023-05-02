package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.service;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Book;

public interface BookService {

    Book validateAndGetBook(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);
}
