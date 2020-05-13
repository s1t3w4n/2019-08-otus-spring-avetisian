package ru.otus.hw06.repositories;

import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Comment;

import java.util.List;

public interface CommentRepositoryJPA {
    Comment addComment(String text, Book book);

    List<Comment> getAllBookComments(long bookId);
}
