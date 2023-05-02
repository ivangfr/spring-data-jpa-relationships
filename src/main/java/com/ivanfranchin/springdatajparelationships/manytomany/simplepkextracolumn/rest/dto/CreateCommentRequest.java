package com.ivanfranchin.springdatajparelationships.manytomany.simplepkextracolumn.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @Schema(example = "2")
    @NotNull
    private Long reviewerId;

    @Schema(example = "1")
    @NotNull
    private Long articleId;

    @Schema(example = "This article is very interesting")
    @NotBlank
    private String text;
}
