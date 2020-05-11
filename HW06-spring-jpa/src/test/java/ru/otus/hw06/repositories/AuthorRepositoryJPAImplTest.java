package ru.otus.hw06.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository based on JPA for working with Authors: ")
@DataJpaTest
@Import(AuthorRepositoryJPAImpl.class)
class AuthorRepositoryJPAImplTest {

    @Autowired
    private AuthorRepositoryJPA repository;

    @Autowired
    TestEntityManager entityManager;

    private static final long EXPECTED_INSERTED_ID = 4L;
    private static final long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_FIRST_NAME = "Alexander";
    private static final String DEFAULT_AUTHOR_LAST_NAME = "Pushkin";

    @DisplayName("Should save Author in Data Base")
    @Test
    void shouldSaveAuthorInDataBase() {
        Author expected = new Author(0, "Jack", "London");
        repository.save(expected);
        Author actual = entityManager.find(Author.class, EXPECTED_INSERTED_ID);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Should return Author with current id")
    @Test
    void shouldReturnAuthorById() {
        val optionalAuthor = repository.getById(DEFAULT_AUTHOR_ID);
        val expectedAuthor = entityManager.find(Author.class, DEFAULT_AUTHOR_ID);
        assertThat(optionalAuthor).isPresent().get()
                .isEqualToComparingFieldByField(expectedAuthor);
    }

    @DisplayName("Should return Author with current full name")
    @Test
    void shouldReturnAuthorByFullName() {
        val optionalAuthor = repository.getByFullName(DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
        val expectedAuthor = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
        assertThat(optionalAuthor).isPresent().get()
                .isEqualToComparingFieldByField(expectedAuthor);
    }

    @DisplayName("Should Not return Author with current full name")
    @Test
    void shouldNotReturnAuthorByFullName() {
        val optionalAuthor = repository.getByFullName("asdasd", "zxczxcxzc");
        assertThat(!optionalAuthor.isPresent());
    }
}