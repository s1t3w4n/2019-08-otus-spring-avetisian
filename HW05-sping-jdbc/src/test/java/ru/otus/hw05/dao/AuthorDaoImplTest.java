package ru.otus.hw05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao for working with Authors have to:")
@JdbcTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    private static final long EXPECTED_INSERTED_ID = 2L;
    private static final int DEFAULT_AUTHOR_ID = 1;
    private static final String DEFAULT_AUTHOR_FIRST_NAME = "Alexander";
    private static final String DEFAULT_AUTHOR_LAST_NAME = "Pushkin";

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("Should insert Author in Data Base")
    @Test
    void shouldInsertAuthorInDataBase() {
        Author expected = new Author(EXPECTED_INSERTED_ID, "Jack", "London");
        authorDao.insert(expected);
        Author actual = authorDao.getById(EXPECTED_INSERTED_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Should return Author with current id")
    @Test
    void shouldReturnAuthorById() {
        Author expected = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
        Author actual = authorDao.getById(DEFAULT_AUTHOR_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Should return Author with current full name")
    @Test
    void shouldReturnAuthorByFullName() {
        Author expected = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
        Author actual = authorDao.getByFullName(DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}