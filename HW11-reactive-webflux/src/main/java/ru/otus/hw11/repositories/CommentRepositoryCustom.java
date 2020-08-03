package ru.otus.hw11.repositories;

import reactor.core.publisher.Flux;
import ru.otus.hw11.models.Comment;

public interface CommentRepositoryCustom {
    Flux<Comment> findAllByBook_Id(String id);
}
