package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateStudentRequest(@Schema(example = "Steve Jobs") String name) {
}
