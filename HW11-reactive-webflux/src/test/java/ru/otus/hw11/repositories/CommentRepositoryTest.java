package ru.otus.hw11.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw11.models.Book;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.hw11.repositories"})
@ComponentScan({"ru.otus.hw11.mongock"})
@DisplayName("Repository based on Spring Reactive Data Mongo for working with Comments: ")
class CommentRepositoryTest {

    public static final String BOOK_ID = "5f3009a749f3ae78c5195dee";
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("Should delete all book comments")
    @Test
    void deleteAllByBook_Id() {
        StepVerifier.create(commentRepository.findAll())
                .expectNextCount(3L)
                .expectComplete()
                .verify();
        StepVerifier.create(commentRepository.deleteAllByBook_Id(BOOK_ID))
                .expectComplete()
                .verify();
        StepVerifier.create(commentRepository.findAll())
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void findAllByBook_Id() {
        StepVerifier.create(commentRepository.findAllByBook_Id(BOOK_ID))
                .expectNextCount(2L)
                .expectComplete()
                .verify();
    }
}