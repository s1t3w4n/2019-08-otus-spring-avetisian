package ru.otus.hw07.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw07.models.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @EntityGraph(value = "comments-entity-graph")
    List<Comment> findByBookId(long bookId);
}
