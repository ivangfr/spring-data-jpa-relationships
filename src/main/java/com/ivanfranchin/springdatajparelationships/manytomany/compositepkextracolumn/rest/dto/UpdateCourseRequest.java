package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCourseRequest(@Schema(example = "Java 9") String name) {
}
