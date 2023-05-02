package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "restaurant")
@EqualsAndHashCode(exclude = "restaurant")
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    public void addRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.getDishes().add(this);
    }

    public void removeRestaurant() {
        this.restaurant.getDishes().remove(this);
        this.restaurant = null;
    }
}
