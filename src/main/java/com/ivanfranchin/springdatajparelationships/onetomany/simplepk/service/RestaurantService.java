package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.service;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Restaurant;

public interface RestaurantService {

    Restaurant validateAndGetRestaurant(Long id);

    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Restaurant restaurant);
}
