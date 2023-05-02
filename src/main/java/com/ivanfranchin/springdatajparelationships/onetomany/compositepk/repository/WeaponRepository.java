package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.repository;

import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Weapon;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.WeaponPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends CrudRepository<Weapon, WeaponPk> {
}
