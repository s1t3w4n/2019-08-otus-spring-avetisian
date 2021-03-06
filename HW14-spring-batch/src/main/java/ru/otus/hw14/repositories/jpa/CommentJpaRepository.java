package ru.otus.hw14.repositories.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14.models.jpa.Comment;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(value = "comments-entity-graph")
    List<Comment> findAll();
}
