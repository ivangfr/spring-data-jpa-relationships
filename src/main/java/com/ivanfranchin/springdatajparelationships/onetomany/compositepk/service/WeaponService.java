package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.service;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Weapon;

public interface WeaponService {

    Weapon validateAndGetWeapon(Long playerId, Long weaponId);

    Weapon saveWeapon(Weapon weapon);

    void deleteWeapon(Weapon weapon);
}
