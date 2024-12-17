package com.ivanfranchin.springdatajparelationships.manytomany.compositepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCourseStudentRequest(@Schema(example = "9") @NotNull @Min(value = 0) @Max(value = 10) Short grade) {
}
