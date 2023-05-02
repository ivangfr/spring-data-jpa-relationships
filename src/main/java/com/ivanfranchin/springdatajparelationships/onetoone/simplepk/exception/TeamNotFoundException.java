package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(Long id) {
        super(String.format("Team with id '%s' not found", id));
    }
}
