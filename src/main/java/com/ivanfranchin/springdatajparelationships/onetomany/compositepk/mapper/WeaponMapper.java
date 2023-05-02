package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.mapper;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Weapon;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.CreateWeaponRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.WeaponResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player", ignore = true)
    Weapon toWeapon(CreateWeaponRequest createWeaponRequest);

    WeaponResponse toWeaponResponse(Weapon weapon);
}
