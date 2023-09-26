# spring-data-jpa-relationships

The goal of this project is to study the JPA relationships: `one-to-one`, `one-to-many` / `many-to-one`, and `many-to-many`.

## Proof-of-Concepts & Articles

On [ivangfr.github.io](https://ivangfr.github.io), I have compiled my Proof-of-Concepts (PoCs) and articles. You can easily search for the technology you are interested in by using the filter. Who knows, perhaps I have already implemented a PoC or written an article about what you are looking for.

## Additional Readings

- \[**Medium**\] [**Understanding Relationships in JPA: Introduction**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-introduction-5416c8a7c8a9)
- \[**Medium**\] [**Understanding Relationships in JPA: One-to-One with Simple Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-one-with-simple-primary-key-7c32f7e13a6a)
- \[**Medium**\] [**Understanding Relationships in JPA: One-to-One with Shared Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-one-with-shared-primary-key-36596416fe56)
- \[**Medium**\] [**Understanding Relationships in JPA: One-to-Many with Simple Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-many-with-simple-primary-key-e2e975c67c31)
- \[**Medium**\] [**Understanding Relationships in JPA: One-to-Many with Composite Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-many-with-composite-primary-key-1d7724a2bf63)
- \[**Medium**\] [**Understanding Relationships in JPA: Many-to-Many with Simple Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-many-to-many-with-simple-primary-key-b38209e5c9b4)
- \[**Medium**\] [**Understanding Relationships in JPA: Many-to-Many with Simple Primary Key and Extra Column**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-many-to-many-with-simple-primary-key-and-extra-column-817e8bdda465)
- \[**Medium**\] [**Understanding Relationships in JPA: Many-to-Many with Composite Primary Key and Extra Column**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-many-to-many-with-composite-primary-key-and-extra-column-a939b107c7cd)
- \[**Medium**\] [**Mastering JPA Relationships: Practical Examples of Bidirectional Associations**](https://medium.com/spring-boot/spring-data-jpa-6bb5cd745b46)

## Prerequisites

- [`Java 17+`](https://www.oracle.com/java/technologies/downloads/#java17)
- [`Docker`](https://www.docker.com/)

## Start Environment

In a terminal and inside `spring-data-jpa-relationships` root folder, run the following command
```
docker compose up -d
```

## Running application using Maven

In a terminal and inside `spring-data-jpa-relationships` root folder, run the command below
```
./mvnw clean spring-boot:run
```

Once the application is running, you can access its Swagger website at http://localhost:8080/swagger-ui.html

## Useful Commands

- **Postgres**
  ```
  docker exec -it postgres psql -U postgres -d jparelationshipsdb
  \d persons
  select * from persons;
  ```
  > Type `exit` to exit

## Shutdown

- To stop the application, go to the terminal where it is running and press `Ctrl+C`
- To stop and remove docker compose containers, network and volumes, go to a terminal and, inside `spring-data-jpa-relationships` root folder, run the following command
  ```
  docker compose down -v
  ```

## Running Tests

In a terminal and inside `spring-data-jpa-relationships` root folder, run the following command
```
./mvnw clean test
```

## JPA relationships

### One-to-One with Simple Primary Key

![teams_team_details](documentation/teams_team_details.png)

\[**Medium**\]: [**Understanding Relationships in JPA: One-to-One with Simple Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-one-with-simple-primary-key-7c32f7e13a6a)

### One-to-One with Shared Primary Key

![persons_person_details](documentation/persons_person_details.png)

\[**Medium**\] [**Understanding Relationships in JPA: One-to-One with Shared Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-one-with-shared-primary-key-36596416fe56)

### One-to-Many with Simple Primary Key

![restaurants_dishes](documentation/restaurants_dishes.png)

\[**Medium**\] [**Understanding Relationships in JPA: One-to-Many with Simple Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-many-with-simple-primary-key-e2e975c67c31)

### One-to-Many with Composite Primary Key

![players_weapons](documentation/players_weapons.png)

\[**Medium**\] [**Understanding Relationships in JPA: One-to-Many with Composite Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-one-to-many-with-composite-primary-key-1d7724a2bf63)

### Many-to-Many with Simple Primary Key

![writers_books](documentation/writers_books.png)

\[**Medium**\] [**Understanding Relationships in JPA: Many-to-Many with Simple Primary Key**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-many-to-many-with-simple-primary-key-b38209e5c9b4)

### Many-to-Many with Simple Primary Key and Extra Column

![reviewers_articles](documentation/reviewers_comments_articles.png)

\[**Medium**\] [**Understanding Relationships in JPA: Many-to-Many with Simple Primary Key and Extra Column**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-many-to-many-with-simple-primary-key-and-extra-column-817e8bdda465)

### Many-to-Many with Composite Primary Key and Extra Column

![students_courses](documentation/students_courses.png)

\[**Medium**\] [**Understanding Relationships in JPA: Many-to-Many with Composite Primary Key and Extra Column**](https://medium.com/@ivangfr/understanding-relationships-in-jpa-many-to-many-with-composite-primary-key-and-extra-column-a939b107c7cd)

## References

One-to-One 
- https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate
- https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-one-mapping-example/

One-to-Many / Many-to-One
- https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate
- https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/

Many-to-Many
- https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate
- https://www.callicoder.com/hibernate-spring-boot-jpa-many-to-many-mapping-example/