package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @Schema(example = "Introduction to Java 8")
    @NotBlank
    private String name;
}
