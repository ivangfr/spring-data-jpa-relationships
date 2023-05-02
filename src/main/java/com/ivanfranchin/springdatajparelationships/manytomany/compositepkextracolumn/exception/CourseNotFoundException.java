package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long id) {
        super(String.format("Course with id '%s' not found", id));
    }
}
