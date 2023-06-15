package com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest;

import com.ivanfranchin.springdatajparelationships.MyContainers;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Dish;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.model.Restaurant;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.repository.DishRepository;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.repository.RestaurantRepository;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.CreateDishRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.CreateRestaurantRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.DishResponse;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.RestaurantResponse;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.UpdateDishRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.simplepk.rest.dto.UpdateRestaurantRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
)
@ImportTestcontainers(MyContainers.class)
class RestaurantDishControllerTest implements MyContainers {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository.deleteAll();
        dishRepository.deleteAll();
    }

    @Test
    void testGetRestaurant() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());

        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_URL, restaurant.getId());
        ResponseEntity<RestaurantResponse> responseEntity = testRestTemplate.getForEntity(url, RestaurantResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(restaurant.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(restaurant.getName());
        assertThat(responseEntity.getBody().dishes().size()).isEqualTo(0);
    }

    @Test
    void testCreateRestaurant() {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest("Happy Pizza");
        ResponseEntity<RestaurantResponse> responseEntity = testRestTemplate.postForEntity(
                API_RESTAURANTS_URL, createRestaurantRequest, RestaurantResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createRestaurantRequest.getName());
        assertThat(responseEntity.getBody().dishes().size()).isEqualTo(0);

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(responseEntity.getBody().id());
        assertThat(restaurantOptional.isPresent()).isTrue();
        restaurantOptional.ifPresent(r -> assertThat(r.getName()).isEqualTo(createRestaurantRequest.getName()));
    }

    @Test
    void testUpdateRestaurant() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());
        UpdateRestaurantRequest updateRestaurantRequest = new UpdateRestaurantRequest("Happy Burger");

        HttpEntity<UpdateRestaurantRequest> requestUpdate = new HttpEntity<>(updateRestaurantRequest);
        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_URL, restaurant.getId());
        ResponseEntity<RestaurantResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, requestUpdate, RestaurantResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(restaurant.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(updateRestaurantRequest.getName());
        assertThat(responseEntity.getBody().dishes().size()).isEqualTo(0);

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getId());
        assertThat(restaurantOptional.isPresent()).isTrue();
        restaurantOptional.ifPresent(r -> assertThat(r.getName()).isEqualTo(updateRestaurantRequest.getName()));
    }

    @Test
    void testDeleteRestaurant() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());

        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_URL, restaurant.getId());
        ResponseEntity<RestaurantResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, RestaurantResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(restaurant.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(restaurant.getName());
        assertThat(responseEntity.getBody().dishes().size()).isEqualTo(0);

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getId());
        assertThat(restaurantOptional.isPresent()).isFalse();
    }

    @Test
    void testGetDish() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());

        Dish dish = getDefaultDish();
        dish.addRestaurant(restaurant);
        dish = dishRepository.save(dish);

        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_DISHES_DISH_ID_URL, restaurant.getId(), dish.getId());
        ResponseEntity<DishResponse> responseEntity = testRestTemplate.getForEntity(url, DishResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(dish.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(dish.getName());
    }

    @Test
    void testCreateDish() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());
        CreateDishRequest createDishRequest = new CreateDishRequest("Pizza Salami");

        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_DISHES_URL, restaurant.getId());
        ResponseEntity<DishResponse> responseEntity = testRestTemplate.postForEntity(
                url, createDishRequest, DishResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createDishRequest.getName());

        Optional<Dish> dishOptional = dishRepository.findById(responseEntity.getBody().id());
        assertThat(dishOptional.isPresent()).isTrue();
        dishOptional.ifPresent(d -> {
            assertThat(d.getRestaurant().getId()).isEqualTo(restaurant.getId());
            assertThat(d.getName()).isEqualTo(createDishRequest.getName());
        });

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getId());
        assertThat(restaurantOptional.isPresent()).isTrue();
        restaurantOptional.ifPresent(r -> {
            assertThat(r.getDishes().size()).isEqualTo(1);
            dishOptional.ifPresent(d -> assertThat(r.getDishes().contains(d)).isTrue());
        });
    }

    @Test
    void testUpdateDish() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());

        Dish dish = getDefaultDish();
        dish.addRestaurant(restaurant);
        dish = dishRepository.save(dish);

        UpdateDishRequest updateDishRequest = new UpdateDishRequest("Pizza Fungi");

        HttpEntity<UpdateDishRequest> requestUpdate = new HttpEntity<>(updateDishRequest);
        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_DISHES_DISH_ID_URL, restaurant.getId(), dish.getId());
        ResponseEntity<DishResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, requestUpdate, DishResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(dish.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(updateDishRequest.getName());

        Optional<Dish> dishOptional = dishRepository.findById(dish.getId());
        assertThat(dishOptional.isPresent()).isTrue();
        dishOptional.ifPresent(d -> assertThat(d.getName()).isEqualTo(updateDishRequest.getName()));
    }

    @Test
    void testDeleteDish() {
        Restaurant restaurant = restaurantRepository.save(getDefaultRestaurant());

        Dish dishAux = getDefaultDish();
        dishAux.addRestaurant(restaurant);
        final Dish dish = dishRepository.save(dishAux);

        String url = String.format(API_RESTAURANTS_RESTAURANT_ID_DISHES_DISH_ID_URL, restaurant.getId(), dish.getId());
        ResponseEntity<DishResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, DishResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(dish.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(dish.getName());

        Optional<Dish> dishOptional = dishRepository.findById(dish.getId());
        assertThat(dishOptional.isPresent()).isFalse();

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getId());
        assertThat(restaurantOptional.isPresent()).isTrue();
        restaurantOptional.ifPresent(r -> {
            assertThat(r.getDishes().size()).isEqualTo(0);
            assertThat(r.getDishes().contains(dish)).isFalse();
        });
    }

    private Restaurant getDefaultRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Happy Pizza");
        return restaurant;
    }

    private Dish getDefaultDish() {
        Dish dish = new Dish();
        dish.setName("Pizza Salami");
        return dish;
    }

    private static final String API_RESTAURANTS_URL = "/api/restaurants";
    private static final String API_RESTAURANTS_RESTAURANT_ID_URL = "/api/restaurants/%s";
    private static final String API_RESTAURANTS_RESTAURANT_ID_DISHES_URL = "/api/restaurants/%s/dishes";
    private static final String API_RESTAURANTS_RESTAURANT_ID_DISHES_DISH_ID_URL = "/api/restaurants/%s/dishes/%s";
}