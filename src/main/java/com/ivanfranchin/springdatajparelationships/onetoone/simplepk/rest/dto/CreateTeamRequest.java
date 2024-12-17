package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateTeamRequest(@Schema(example = "Team White") @NotBlank String name) {
}
