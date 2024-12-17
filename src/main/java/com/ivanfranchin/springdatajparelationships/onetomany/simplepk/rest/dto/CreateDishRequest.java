package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateDishRequest(@Schema(example = "Pizza Salami") @NotBlank String name) {
}
