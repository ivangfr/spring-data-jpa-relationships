package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateArticleRequest(
        @Schema(example = "Comparison between Springboot and Play Framework") @NotBlank String title) {
}
