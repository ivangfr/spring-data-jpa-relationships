package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateRestaurantRequest(@Schema(example = "Happy Burger") String name) {
}
