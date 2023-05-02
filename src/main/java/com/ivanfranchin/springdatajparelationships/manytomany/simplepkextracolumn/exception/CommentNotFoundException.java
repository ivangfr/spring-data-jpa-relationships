package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super(String.format("Comment with id '%s' not found", id));
    }
}
