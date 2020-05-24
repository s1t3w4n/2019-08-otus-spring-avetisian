package ru.otus.hw07.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw07.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository based on Spring Data for working with Genre:")
@DataJpaTest
class GenreRepositoryTest {
    private static final String DEFAULT_GENRE = "novel";
    private static final Genre EXPECTED_GENRE = new Genre(1L, DEFAULT_GENRE);

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("Should return Genre with current genre")
    @Test
    void shouldReturnGenreByGenre() {
        val optionalGenre = genreRepository.findByGenre(DEFAULT_GENRE);
        assertThat(optionalGenre).isPresent().get().isEqualToComparingFieldByField(EXPECTED_GENRE);
    }
}