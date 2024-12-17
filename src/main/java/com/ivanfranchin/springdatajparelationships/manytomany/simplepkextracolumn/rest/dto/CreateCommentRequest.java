package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
        @Schema(example = "2") @NotNull Long reviewerId,
        @Schema(example = "1") @NotNull Long articleId,
        @Schema(example = "This article is very interesting") @NotBlank String text) {
}
