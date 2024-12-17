package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model;

import java.io.Serializable;

public record WeaponPk(Long id,Long player) implements Serializable {
}
