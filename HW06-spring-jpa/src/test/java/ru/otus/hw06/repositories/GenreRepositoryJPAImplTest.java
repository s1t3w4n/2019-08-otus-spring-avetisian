package ru.otus.hw06.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository based on JPA for working with Genre:")
@DataJpaTest
@Import(GenreRepositoryJPAImpl.class)
class GenreRepositoryJPAImplTest {
    private static final long EXPECTED_INSERTED_ID = 4L;
    private static final long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE = "novel";

    @Autowired
    private GenreRepositoryJPA repository;

    @Autowired
    TestEntityManager entityManager;

    @DisplayName("Should save Genre in Data Base")
    @Test
    void shouldSaveGenreInDataBase() {
        Genre expected = new Genre(0, "criminal");
        repository.save(expected);
        Genre actual = entityManager.find(Genre.class, EXPECTED_INSERTED_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Should return Genre with current id")
    @Test
    void shouldReturnGenreById() {
        val optionalGenre = repository.getById(DEFAULT_GENRE_ID);
        val expectedGenre = entityManager.find(Genre.class, DEFAULT_GENRE_ID);
        assertThat(optionalGenre).isPresent().get()
                .isEqualToComparingFieldByField(expectedGenre);
    }

    @DisplayName("Should return Genre with current genre")
    @Test
    void shouldReturnGenreByGenre() {
        val optionalGenre = repository.getByGenre(DEFAULT_GENRE);
        val expectedGenre = entityManager.find(Genre.class, DEFAULT_GENRE_ID);
        assertThat(optionalGenre).isPresent().get()
                .isEqualToComparingFieldByField(expectedGenre);
    }

}