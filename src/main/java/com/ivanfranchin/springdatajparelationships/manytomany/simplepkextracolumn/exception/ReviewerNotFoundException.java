package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewerNotFoundException extends RuntimeException {

    public ReviewerNotFoundException(Long id) {
        super(String.format("Reviewer with id '%s' not found", id));
    }
}
