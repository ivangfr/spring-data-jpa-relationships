package com.ivanfranchin.springdatajparelationships;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.postgresql.PostgreSQLContainer;

public interface MyContainers {

    @Container
    @ServiceConnection
    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:18.0");
}
