package ru.otus.hw08.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.hw08.models.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.hw08.repositories"})
@ComponentScan({"ru.otus.hw08.mongock"})
@DisplayName("Repository based on Spring Data Mongo for working with Books: ")
class BookRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_STUDENTS = 3;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Should return all books from Data Base")
    @Test
    void shouldReturnAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_STUDENTS)
                .allMatch(book -> !book.getTitle().isEmpty())
                .allMatch(book -> book.getAuthor() != null)
                .allMatch(book -> book.getGenre() != null)
                .extracting(Book::getId)
                .containsExactly(1L, 2L, 3L);
    }
}