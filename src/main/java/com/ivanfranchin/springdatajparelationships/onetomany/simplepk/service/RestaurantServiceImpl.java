package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.service;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.exception.RestaurantNotFoundException;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Restaurant;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant validateAndGetRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }
}
