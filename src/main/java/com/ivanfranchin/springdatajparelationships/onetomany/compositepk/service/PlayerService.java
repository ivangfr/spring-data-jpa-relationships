package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.service;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Player;

public interface PlayerService {

    Player validateAndGetPlayer(Long id);

    Player savePlayer(Player player);

    void deletePlayer(Player player);
}
