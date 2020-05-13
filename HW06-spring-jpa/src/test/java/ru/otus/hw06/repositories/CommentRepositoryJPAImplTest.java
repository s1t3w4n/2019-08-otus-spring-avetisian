package ru.otus.hw06.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository based on JPA for working with Comments: ")
@DataJpaTest
@Import(CommentRepositoryJPAImpl.class)
class CommentRepositoryJPAImplTest {

    private static final String SAVED_TEXT = "The best book in the world";
    private static final long DEFAULT_BOOK_ID = 1L;
    private static final long EXPECTED_COMMENT_ID = 3L;
    private static final int EXPECTED_COMMENTS_SIZE = 2;

    @Autowired
    private CommentRepositoryJPA repository;

    @Autowired
    TestEntityManager entityManager;

    @DisplayName("Should add a comment to Data Base")
    @Test
    void shouldAddCommentToDataBase() {
        repository.addComment(SAVED_TEXT, entityManager.find(Book.class, DEFAULT_BOOK_ID));
        assertThat(entityManager.find(Comment.class, EXPECTED_COMMENT_ID).getText()).isEqualTo(SAVED_TEXT);
    }

    @DisplayName("Should return all the book's comments")
    @Test
    void  shouldReturnAllBooksComments() {
        List<Comment> booksComment = repository.getAllBookComments(DEFAULT_BOOK_ID);
        assertThat(booksComment).isNotNull().hasSize(EXPECTED_COMMENTS_SIZE);
    }
 }