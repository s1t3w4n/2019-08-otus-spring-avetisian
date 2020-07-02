package ru.otus.hw10.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.models.Comment;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    Book createBook(String tittle, String firstName, String lastName, String genre);

    Optional<Book> readById(long id);

    Book updateBook(long id, String tittle, String firstName, String lastName, String genre);

    void deleteById(long id);

    List<Book> readAllBooks();

    List<Long> getAllBooksIDs();

    List<String> getAllFirsNames();

    List<String> getAllLastNames();

    List<String> getAllGenre();

    List<Comment> getBookComments(long bookId);

    void leaveCommentToBook(Book book, String text);

    List<Comment> readAllComments();
}
