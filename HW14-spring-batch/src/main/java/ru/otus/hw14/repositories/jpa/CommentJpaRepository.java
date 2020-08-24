package ru.otus.hw14.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw14.models.jpa.Comment;

public interface CommentJpaRepository extends CrudRepository<Comment, Long> {
}
