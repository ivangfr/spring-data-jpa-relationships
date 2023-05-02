package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super(String.format("Player with id '%s' not found", id));
    }
}
