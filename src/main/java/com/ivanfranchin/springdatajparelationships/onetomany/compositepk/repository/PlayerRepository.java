package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.repository;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
