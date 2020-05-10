package ru.otus.hw06.repositories;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06.models.Author;
import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;

@DisplayName("Repository based on JPA for working with Books: ")
@DataJpaTest
@Import(BookRepositoryJPAImpl.class)
class BookRepositoryJPAImplTest {

    private static final String EXPECTED_INSERTED_TITLE = "Ruslan and Ludmila";
    private static final String DEFAULT_BOOK_TITTLE = "Captain`s daughter";
    private static final long EXPECTED_SAVED_ID = 4L;
    private static final Author DEFAULT_AUTHOR = new Author(1, "Alexander", "Pushkin");
    private static final Genre DEFAULT_GENRE = new Genre(1, "novel");
    private static final long DEFAULT_BOOK_ID = 1L;
    private static final int EXPECTED_NUMBER_OF_STUDENTS = 3;

    @Autowired
    private BookRepositoryJPA repository;

    @Autowired
    TestEntityManager entityManager;

    @DisplayName("should get expected book by id")
    @Test
    void shouldGetExpectedBookById() {
        Book expected = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_TITTLE, DEFAULT_AUTHOR, DEFAULT_GENRE);
        Book actual = repository.findById(DEFAULT_BOOK_ID).orElse(null);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("should find in database expected book by id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalBook = repository.findById(DEFAULT_BOOK_ID);
        val expectedBook = entityManager.find(Book.class, DEFAULT_BOOK_ID);
        assertThat(optionalBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("Should return all books from Data Base")
    @Test
    void shouldReturnAllBooks() {
        List<Book> books = repository.getAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(book -> !book.getTitle().isEmpty())
                .allMatch(book -> book.getAuthor() != null)
                .allMatch(book -> book.getGenre() != null)
                .extracting(Book::getId)
                .containsExactly(1L, 2L, 3L);
    }

    @DisplayName("Should delete Book in Data Base by id")
    @Test
    void shouldDeleteBookById() {
        SoftAssertions softly = new SoftAssertions();
        val deletableBook = entityManager.find(Book.class, DEFAULT_BOOK_ID);
        softly.assertThat(deletableBook).isNotNull();
        entityManager.detach(deletableBook);
        repository.deleteById(DEFAULT_BOOK_ID);
        val deletedBook = entityManager.find(Book.class, DEFAULT_BOOK_ID);
        softly.assertThat(deletedBook).isNull();
        softly.assertAll();
    }

    @DisplayName("Should save new Book in Data Base")
    @Test
    void shouldSaveAllBookInfo() {
        Book expected = new Book(EXPECTED_SAVED_ID, EXPECTED_INSERTED_TITLE, DEFAULT_AUTHOR, DEFAULT_GENRE);
        repository.save(expected);
        Book actual = entityManager.find(Book.class, EXPECTED_SAVED_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Should update Book in Data Base")
    @Test
    void shouldUpdateBookInDataBase() {
        Book expected = new Book(DEFAULT_BOOK_ID, EXPECTED_INSERTED_TITLE, DEFAULT_AUTHOR, DEFAULT_GENRE);
        repository.save(expected);
        Book actual = entityManager.find(Book.class, DEFAULT_BOOK_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}