package ru.otus.hw11.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.hw11.repositories"})
@ComponentScan({"ru.otus.hw11.mongock"})
@DisplayName("Repository based on Spring Reactive Data Mongo for working with Genre: ")
class GenreRepositoryTest {

    private static final String NOVEL = "novel";
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findByGenre() {
        StepVerifier.create(genreRepository.findByGenre(NOVEL))
                .assertNext(genre -> {
                    assertThat(genre).isNotNull();
                    assertThat(genre.getGenre()).isEqualTo(NOVEL);
                })
                .expectComplete()
                .verifyThenAssertThat()
                .tookLessThan(Duration.ofMillis(500));
    }
}