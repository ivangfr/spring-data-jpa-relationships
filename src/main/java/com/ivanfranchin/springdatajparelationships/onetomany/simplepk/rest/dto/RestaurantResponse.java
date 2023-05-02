package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto;

import java.util.List;

public record RestaurantResponse(Long id, String name, List<DishResponse> dishes) {
}
