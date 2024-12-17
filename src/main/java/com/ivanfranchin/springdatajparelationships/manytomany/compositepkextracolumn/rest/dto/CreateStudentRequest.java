package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateStudentRequest(@Schema(example = "Ivan Franchin") @NotBlank String name) {
}
