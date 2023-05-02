package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(Long dishId, Long restaurantId) {
        super(String.format("Restaurant with id '%s' doesn't have dish with id '%s'", restaurantId, dishId));
    }
}
