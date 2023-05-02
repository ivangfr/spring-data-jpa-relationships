package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest;

import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.mapper.DishMapper;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.mapper.RestaurantMapper;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Dish;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Restaurant;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.CreateDishRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.CreateRestaurantRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.DishResponse;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.RestaurantResponse;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.UpdateDishRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.UpdateRestaurantRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.service.DishService;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantDishController {

    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final RestaurantMapper restaurantMapper;
    private final DishMapper dishMapper;

    //-----------
    // Restaurant

    @GetMapping("/{restaurantId}")
    public RestaurantResponse getRestaurant(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestaurantResponse createRestaurant(@Valid @RequestBody CreateRestaurantRequest createRestaurantRequest) {
        Restaurant restaurant = restaurantMapper.toRestaurant(createRestaurantRequest);
        restaurant = restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantResponse updateRestaurant(@PathVariable Long restaurantId,
                                               @Valid @RequestBody UpdateRestaurantRequest updateRestaurantRequest) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        restaurantMapper.updateRestaurantFromRequest(updateRestaurantRequest, restaurant);
        restaurantService.saveRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public RestaurantResponse deleteRestaurant(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        restaurantService.deleteRestaurant(restaurant);
        return restaurantMapper.toRestaurantResponse(restaurant);
    }

    //-----
    // Dish

    @GetMapping("/{restaurantId}/dishes/{dishId}")
    public DishResponse getDish(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        Dish dish = dishService.validateAndGetDish(dishId, restaurantId);
        return dishMapper.toDishResponse(dish);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{restaurantId}/dishes")
    public DishResponse createDish(@PathVariable Long restaurantId,
                                   @Valid @RequestBody CreateDishRequest createDishRequest) {
        Restaurant restaurant = restaurantService.validateAndGetRestaurant(restaurantId);
        Dish dish = dishMapper.toDish(createDishRequest);
        dish.addRestaurant(restaurant);
        dish = dishService.saveDish(dish);
        return dishMapper.toDishResponse(dish);
    }

    @PutMapping("/{restaurantId}/dishes/{dishId}")
    public DishResponse updateDish(@PathVariable Long restaurantId,
                                   @PathVariable Long dishId,
                                   @Valid @RequestBody UpdateDishRequest updateDishRequest) {
        Dish dish = dishService.validateAndGetDish(dishId, restaurantId);
        dishMapper.updateDishFromRequest(updateDishRequest, dish);
        dish = dishService.saveDish(dish);
        return dishMapper.toDishResponse(dish);
    }

    @DeleteMapping("/{restaurantId}/dishes/{dishId}")
    public DishResponse deleteDish(@PathVariable Long restaurantId, @PathVariable Long dishId) {
        Dish dish = dishService.validateAndGetDish(dishId, restaurantId);
        dish.removeRestaurant();
        dishService.deleteDish(dish);
        return dishMapper.toDishResponse(dish);
    }
}
