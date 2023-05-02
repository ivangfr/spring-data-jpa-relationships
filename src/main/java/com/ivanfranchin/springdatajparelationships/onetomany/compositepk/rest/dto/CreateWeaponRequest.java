package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWeaponRequest {

    @Schema(example = "Machine Gun")
    @NotBlank
    private String name;
}
