package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.service;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Dish;

public interface DishService {

    Dish validateAndGetDish(Long dishId, Long restaurantId);

    Dish saveDish(Dish dish);

    void deleteDish(Dish dish);
}
