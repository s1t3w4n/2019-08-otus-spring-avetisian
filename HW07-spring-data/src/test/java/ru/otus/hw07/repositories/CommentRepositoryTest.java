package ru.otus.hw07.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw07.models.Comment;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository based on Spring Data for working with Comments: ")
@DataJpaTest
class CommentRepositoryTest {

    private static final long DEFAULT_BOOK_ID = 1;
    private static final int EXPECTED_COMMENTS_SIZE = 2;
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("Should return all the book's comments")
    @Test
    void shouldReturnAllBooksComments() {
        List<Comment> booksComments = commentRepository.findByBookId(DEFAULT_BOOK_ID);
        assertThat(booksComments).isNotNull().hasSize(EXPECTED_COMMENTS_SIZE)
                .allMatch(Objects::nonNull);
    }
}