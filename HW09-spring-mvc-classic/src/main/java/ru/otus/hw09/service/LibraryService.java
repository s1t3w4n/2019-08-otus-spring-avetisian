package ru.otus.hw09.service;

import ru.otus.hw09.models.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    Book createBook(String tittle, String firstName, String lastName, String genre);

    Optional<Book> readById(long id);

    Book updateBook(long id, String tittle, String firstName, String lastName, String genre);

    String deleteById(long id);

    List<Book> readAllBooks();

    String leaveCommentToBook(long bookId, String text);

    String readAllComments();
}
