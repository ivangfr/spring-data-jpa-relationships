package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto;

import java.util.List;

public record PlayerResponse(Long id, String name, List<Weapon> weapons) {

    public record Weapon(Long id, String name) {
    }
}
