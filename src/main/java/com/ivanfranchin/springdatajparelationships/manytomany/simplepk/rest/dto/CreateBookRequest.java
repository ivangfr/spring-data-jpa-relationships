package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(@Schema(example = "Introduction to Java 8") @NotBlank String name) {
}
