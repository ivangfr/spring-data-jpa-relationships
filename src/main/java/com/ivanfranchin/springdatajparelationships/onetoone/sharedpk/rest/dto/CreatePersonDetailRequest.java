package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreatePersonDetailRequest(
        @Schema(example = "More information about the person") @NotBlank String description) {
}
