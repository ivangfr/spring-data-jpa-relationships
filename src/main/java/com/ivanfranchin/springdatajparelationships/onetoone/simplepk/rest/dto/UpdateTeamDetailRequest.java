package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamDetailRequest {

    @Schema(example = "This team is excellent")
    private String description;
}
