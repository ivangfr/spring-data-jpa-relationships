package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponPk implements Serializable {

    private Long id;
    private Long player;
}
