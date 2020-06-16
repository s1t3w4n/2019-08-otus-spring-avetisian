package ru.otus.hw08.repositories;

import ru.otus.hw08.models.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findAllByBook_Id(long id);
}
