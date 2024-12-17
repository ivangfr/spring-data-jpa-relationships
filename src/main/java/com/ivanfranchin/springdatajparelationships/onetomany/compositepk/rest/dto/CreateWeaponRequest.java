package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateWeaponRequest(@Schema(example = "Machine Gun") @NotBlank String name) {
}
