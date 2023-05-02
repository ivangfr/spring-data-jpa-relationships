package com.ivanfranchin.springdatajparelationships.onetoone.sharedpk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonDetailRequest {

    @Schema(example = "New information about the person")
    private String description;
}
