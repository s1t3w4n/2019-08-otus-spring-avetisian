package ru.otus.hw05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao for working with Genre have to:")
@JdbcTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    private static final long EXPECTED_INSERTED_ID = 2L;
    private static final long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE = "novel";
    @Autowired
    private GenreDao genreDao;

    @DisplayName("Should insert Genre in Data Base")
    @Test
    void shouldInsertGenreInDataBase() {
        Genre expected = new Genre(EXPECTED_INSERTED_ID, "criminal");
        genreDao.insert(expected);
        Genre actual = genreDao.getById(EXPECTED_INSERTED_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Should return Genre with current id")
    @Test
    void shouldReturnGenreById() {
        Genre expected = new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE);
        Genre actual = genreDao.getById(DEFAULT_GENRE_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Should return Genre with current genre")
    @Test
    void shouldReturnGenreByGenre() {
        Genre expected = new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE);
        Genre actual = genreDao.getByGenre(DEFAULT_GENRE);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}