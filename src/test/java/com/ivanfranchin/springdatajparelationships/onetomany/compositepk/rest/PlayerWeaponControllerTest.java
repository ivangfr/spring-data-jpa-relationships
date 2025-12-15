package com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest;

import com.ivanfranchin.springdatajparelationships.MyContainers;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Player;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.Weapon;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.model.WeaponPk;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.repository.PlayerRepository;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.repository.WeaponRepository;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.CreatePlayerRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.CreateWeaponRequest;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.PlayerResponse;
import com.ivanfranchin.springdatajparelationships.onetomany.compositepk.rest.dto.WeaponResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestRestTemplate
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
)
@ImportTestcontainers(MyContainers.class)
class PlayerWeaponControllerTest implements MyContainers {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @BeforeEach
    void setUp() {
        playerRepository.deleteAll();
        weaponRepository.deleteAll();
    }

    @Test
    void testGetPlayer() {
        Player player = playerRepository.save(getDefaultPlayer());

        String url = String.format(API_PLAYERS_PLAYER_ID_URL, player.getId());
        ResponseEntity<PlayerResponse> responseEntity = testRestTemplate.getForEntity(url, PlayerResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(player.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(player.getName());
        assertThat(responseEntity.getBody().weapons().size()).isEqualTo(0);
    }

    @Test
    void testCreatePlayer() {
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest("Ivan Franchin");
        ResponseEntity<PlayerResponse> responseEntity = testRestTemplate.postForEntity(
                API_PLAYERS_URL, createPlayerRequest, PlayerResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createPlayerRequest.name());
        assertThat(responseEntity.getBody().weapons().size()).isEqualTo(0);

        Optional<Player> playerOptional = playerRepository.findById(responseEntity.getBody().id());
        assertThat(playerOptional.isPresent()).isTrue();
        playerOptional.ifPresent(p -> assertThat(p.getName()).isEqualTo(createPlayerRequest.name()));
    }

    @Test
    void testDeletePlayer() {
        Player player = playerRepository.save(getDefaultPlayer());

        String url = String.format("/api/players/%s", player.getId());
        ResponseEntity<PlayerResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, PlayerResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(player.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(player.getName());
        assertThat(responseEntity.getBody().weapons().size()).isEqualTo(0);

        Optional<Player> playerOptional = playerRepository.findById(player.getId());
        assertThat(playerOptional.isPresent()).isFalse();
    }

    @Test
    void testGetWeapon() {
        Player player = playerRepository.save(getDefaultPlayer());

        Weapon weapon = getDefaultWeapon();
        weapon.setPlayer(player);
        weapon = weaponRepository.save(weapon);

        String url = String.format(API_PLAYERS_PLAYER_ID_WEAPONS_WEAPON_ID_URL, player.getId(), weapon.getId());
        ResponseEntity<WeaponResponse> responseEntity = testRestTemplate.getForEntity(url, WeaponResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(weapon.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(weapon.getName());
    }

    @Test
    void testAddWeapon() {
        Player player = playerRepository.save(getDefaultPlayer());
        CreateWeaponRequest createWeaponRequest = new CreateWeaponRequest("Machine Gun");

        String url = String.format(API_PLAYERS_PLAYER_ID_WEAPONS_URL, player.getId());
        ResponseEntity<WeaponResponse> responseEntity = testRestTemplate.postForEntity(
                url, createWeaponRequest, WeaponResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createWeaponRequest.name());

        Optional<Weapon> weaponOptional = weaponRepository.findById(
                new WeaponPk(responseEntity.getBody().id(), player.getId()));
        assertThat(weaponOptional.isPresent()).isTrue();
        weaponOptional.ifPresent(w -> assertThat(w.getPlayer().getId()).isEqualTo(player.getId()));

        Optional<Player> playerOptional = playerRepository.findById(player.getId());
        assertThat(playerOptional.isPresent()).isTrue();
        playerOptional.ifPresent(p -> {
            assertThat(p.getWeapons().size()).isEqualTo(1);
            weaponOptional.ifPresent(w -> assertThat(p.getWeapons().contains(w)).isTrue());
        });
    }

    @Test
    void testRemoveWeapon() {
        Player player = playerRepository.save(getDefaultPlayer());

        Weapon weaponAux = getDefaultWeapon();
        weaponAux.setPlayer(player);
        final Weapon weapon = weaponRepository.save(weaponAux);

        String url = String.format(API_PLAYERS_PLAYER_ID_WEAPONS_WEAPON_ID_URL, player.getId(), weapon.getId());
        ResponseEntity<WeaponResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, WeaponResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(weapon.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(weapon.getName());

        Optional<Weapon> weaponOptional = weaponRepository.findById(new WeaponPk(player.getId(), weapon.getId()));
        assertThat(weaponOptional.isPresent()).isFalse();

        Optional<Player> playerOptional = playerRepository.findById(player.getId());
        assertThat(playerOptional.isPresent()).isTrue();
        playerOptional.ifPresent(p -> {
            assertThat(p.getWeapons().size()).isEqualTo(0);
            assertThat(p.getWeapons().contains(weapon)).isFalse();
        });
    }

    private Player getDefaultPlayer() {
        Player player = new Player();
        player.setName("Ivan Franchin");
        return player;
    }

    private Weapon getDefaultWeapon() {
        Weapon weapon = new Weapon();
        weapon.setId(1L);
        weapon.setName("Machine Gun");
        return weapon;
    }

    private static final String API_PLAYERS_URL = "/api/players";
    private static final String API_PLAYERS_PLAYER_ID_URL = "/api/players/%s";
    private static final String API_PLAYERS_PLAYER_ID_WEAPONS_URL = "/api/players/%s/weapons";
    private static final String API_PLAYERS_PLAYER_ID_WEAPONS_WEAPON_ID_URL = "/api/players/%s/weapons/%s";

}