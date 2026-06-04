package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.repository;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Dish;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

  Optional<Dish> findByIdAndRestaurantId(Long id, Long restaurantId);
}
