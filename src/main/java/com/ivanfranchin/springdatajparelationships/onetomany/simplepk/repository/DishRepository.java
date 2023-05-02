package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.repository;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

    Optional<Dish> findByIdAndRestaurantId(Long id, Long restaurantId);
}
