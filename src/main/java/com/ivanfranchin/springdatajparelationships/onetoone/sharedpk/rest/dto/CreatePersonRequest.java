package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreatePersonRequest(@Schema(example = "Ivan Franchin") @NotBlank String name) {
}
