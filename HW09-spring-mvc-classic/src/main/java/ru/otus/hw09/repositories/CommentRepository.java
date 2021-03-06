package ru.otus.hw09.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw09.models.Book;
import ru.otus.hw09.models.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @EntityGraph(value = "comments-entity-graph")
    List<Comment> findByBookId(long bookId);
    @EntityGraph(value = "comments-entity-graph")
    List<Comment> findAll();
    void deleteCommentByBook(Book book);
}
