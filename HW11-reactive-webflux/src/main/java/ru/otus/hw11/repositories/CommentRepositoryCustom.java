package ru.otus.hw11.repositories;

import ru.otus.hw11.models.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findAllByBook_Id(long id);
}
