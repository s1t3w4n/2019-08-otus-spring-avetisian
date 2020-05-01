package ru.otus.hw05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Book;
import ru.otus.hw05.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao for working with Books should:")
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    private static final String EXPECTED_INSERTED_TITLE = "Ruslan and Ludmila";
    private static final String DEFAULT_BOOK_TITTLE = "Captain`s daughter";
    private static final long EXPECTED_INSERTED_ID = 2L;
    private static final Author DEFAULT_AUTHOR = new Author(1, "Alexander", "Pushkin");
    private static final Genre DEFAULT_GENRE = new Genre(1, "novel");
    private static final long DEFAULT_BOOK_ID = 1L;

    @Autowired
    private BookDao bookDao;

    @DisplayName("Should insert Book in Data Base")
    @Test
    void shouldInsertBookInDataBase() {
        Book expected = new Book(EXPECTED_INSERTED_ID, EXPECTED_INSERTED_TITLE, DEFAULT_AUTHOR, DEFAULT_GENRE);
        bookDao.insert(expected);
        Book actual = bookDao.getById(EXPECTED_INSERTED_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Should return Book with current id")
    @Test
    void shouldReturnBookById() {
        Book expected = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_TITTLE, DEFAULT_AUTHOR, DEFAULT_GENRE);
        Book actual = bookDao.getById(DEFAULT_BOOK_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Should update Book in Data Base")
    @Test
    void shouldUpdateBookInDataBase() {
        Book expected = new Book(DEFAULT_BOOK_ID, EXPECTED_INSERTED_TITLE, DEFAULT_AUTHOR, DEFAULT_GENRE);
        bookDao.update(expected);
        Book actual = bookDao.getById(DEFAULT_BOOK_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Should delete Book in Data Base by id")
    @Test
    void shouldDeleteBookInDataBase() {
        bookDao.deleteById(DEFAULT_BOOK_ID);
        assertThat(bookDao.getById(DEFAULT_BOOK_ID)).isNull();
    }

    @DisplayName("Should return all books from Data Base")
    @Test
    void shouldReturnAllBooks() {
        assertThat(bookDao.getAll()).extracting(Book::getBookId)
                .containsExactly(DEFAULT_BOOK_ID);
    }
}