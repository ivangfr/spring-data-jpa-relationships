package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.mapper;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Player;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.CreatePlayerRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weapons", ignore = true)
    Player toPlayer(CreatePlayerRequest createPlayerRequest);

    PlayerResponse toPlayerResponse(Player player);
}
