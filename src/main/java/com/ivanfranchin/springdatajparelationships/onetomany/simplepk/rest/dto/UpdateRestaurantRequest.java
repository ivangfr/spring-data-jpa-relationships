package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRestaurantRequest {

    @Schema(example = "Happy Burger")
    private String name;
}
