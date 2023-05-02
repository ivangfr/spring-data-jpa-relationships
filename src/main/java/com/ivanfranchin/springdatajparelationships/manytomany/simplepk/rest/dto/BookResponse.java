package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import java.util.List;

public record BookResponse(Long id, String name, List<Writer> writers) {

    public record Writer(Long id, String name) {
    }
}
