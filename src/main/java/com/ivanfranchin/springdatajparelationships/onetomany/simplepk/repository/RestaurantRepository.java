package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.repository;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
