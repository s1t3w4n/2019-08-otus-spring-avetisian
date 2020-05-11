package ru.otus.hw06.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository based on JPA for working with Comments: ")
@DataJpaTest
@Import(CommentRepositoryJPAImpl.class)
class CommentRepositoryJPAImplTest {

    private static final String SAVED_TEXT = "The best book in the world";

    @Autowired
    private CommentRepositoryJPA repository;

    @Autowired
    TestEntityManager entityManager;

    @DisplayName("Should add a comment to Data Base")
    @Test
    void shouldAddCommentToDataBase() {
        repository.addComment(SAVED_TEXT);
        assertThat(entityManager.find(Comment.class, 1L).getText()).isEqualTo(SAVED_TEXT);
    }
}