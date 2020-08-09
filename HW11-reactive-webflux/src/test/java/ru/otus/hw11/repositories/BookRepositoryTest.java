package ru.otus.hw11.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import reactor.test.StepVerifier;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.hw11.repositories"})
@ComponentScan({"ru.otus.hw11.mongock"})
@DisplayName("Repository based on Spring Reactive Data Mongo for working with Books: ")
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @DisplayName("Should return only books with titles")
    @Test
    void getAllTitles() {
        StepVerifier.create(repository.getAllTitles())
                .expectNextCount(3L)
                .expectComplete()
                .verify();
    }
}