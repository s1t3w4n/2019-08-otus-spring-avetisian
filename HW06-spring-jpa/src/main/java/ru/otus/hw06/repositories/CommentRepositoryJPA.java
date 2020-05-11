package ru.otus.hw06.repositories;

import ru.otus.hw06.models.Comment;

public interface CommentRepositoryJPA {
    Comment addComment(String text);
}
