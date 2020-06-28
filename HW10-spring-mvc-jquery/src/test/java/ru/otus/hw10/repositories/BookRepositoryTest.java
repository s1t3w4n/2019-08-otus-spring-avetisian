package ru.otus.hw10.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw10.models.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Repository based on Spring Data for working with Books: ")
class BookRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 3;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Should return all books from Data Base")
    @Test
    void shouldReturnAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(book -> !book.getTitle().isEmpty())
                .allMatch(book -> book.getAuthor() != null)
                .allMatch(book -> book.getGenre() != null)
                .extracting(Book::getId)
                .containsExactly(1L, 2L, 3L);
    }
}