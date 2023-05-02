package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.exception;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.WeaponPk;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeaponNotFoundException extends RuntimeException {

    public WeaponNotFoundException(WeaponPk weaponPk) {
        super(String.format("Weapon with id '%s' not found", weaponPk));
    }
}
