package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.mapper;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Dish;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.CreateDishRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.DishResponse;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.UpdateDishRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DishMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Dish toDish(CreateDishRequest createDishRequest);

    DishResponse toDishResponse(Dish dish);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    void updateDishFromRequest(UpdateDishRequest updateDishRequest, @MappingTarget Dish dish);
}
