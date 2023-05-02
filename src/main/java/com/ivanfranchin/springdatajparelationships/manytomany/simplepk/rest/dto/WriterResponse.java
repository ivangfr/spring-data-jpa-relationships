package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import java.util.List;

public record WriterResponse(Long id, String name, List<Book> books) {

    public record Book(Long id, String name) {
    }
}
