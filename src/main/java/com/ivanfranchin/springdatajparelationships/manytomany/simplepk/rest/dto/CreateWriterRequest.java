package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateWriterRequest(@Schema(example = "Ivan Franchin") @NotBlank String name) {
}
