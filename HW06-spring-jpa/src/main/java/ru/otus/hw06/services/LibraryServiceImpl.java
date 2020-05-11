package ru.otus.hw06.services;

import org.springframework.stereotype.Service;
import ru.otus.hw06.models.Author;
import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Comment;
import ru.otus.hw06.models.Genre;
import ru.otus.hw06.repositories.AuthorRepositoryJPA;
import ru.otus.hw06.repositories.BookRepositoryJPA;
import ru.otus.hw06.repositories.CommentRepositoryJPA;
import ru.otus.hw06.repositories.GenreRepositoryJPA;

import java.util.Collections;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookRepositoryJPA bookRepository;
    private final AuthorRepositoryJPA authorRepository;
    private final GenreRepositoryJPA genreRepository;
    private final CommentRepositoryJPA commentRepository;

    private static final String NO_SUCH_ID = "There is no element with such id";

    public LibraryServiceImpl(BookRepositoryJPA bookRepository, AuthorRepositoryJPA authorRepository, GenreRepositoryJPA genreRepository, CommentRepositoryJPA commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public String createBook(String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(new Book(0,
                tittle,
                checkForAuthor(firstName, lastName),
                checkForGenre(genre),
                Collections.emptyList()))
                .toString() + "\nHas added to the library";
    }

    @Override
    public String readById(long id) {
        return bookRepository.findById(id).map(Book::toString).orElse(NO_SUCH_ID);
    }

    @Override
    public String updateBook(long id, String tittle, String firstName, String lastName, String genre) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = bookRepository.save(
                    new Book(id, tittle, checkForAuthor(firstName, lastName), checkForGenre(genre),
                            optionalBook.get().getComments()));
            return optionalBook.get().toString() + "\nHas updated into:\n" + book.toString();
        } else {
            return NO_SUCH_ID;
        }
    }

    @Override
    public String deleteById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return book.get().toString() + "\nHas deleted";
        } else {
            return NO_SUCH_ID;
        }
    }

    @Override
    public String readAllBooks() {
        StringBuilder builder = new StringBuilder("The library contains that books:");
        for (Book book : bookRepository.getAll()) {
            builder.append("\n");
            builder.append(book.toString());
        }
        return builder.toString();
    }

    @Override
    public String leaveCommentToBook(long bookId, String text) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Comment comment = commentRepository.addComment(text);
            optionalBook.get().getComments().add(comment);
            bookRepository.save(optionalBook.get());
            return "Your commentary has added\n" + bookRepository.save(optionalBook.get()).toString();
        } else {
            return NO_SUCH_ID;
        }
    }

    private Author checkForAuthor(String firstName, String lastName) {
        return authorRepository.getByFullName(
                firstName, lastName).orElseGet(() -> new Author(0, firstName, lastName));
    }

    private Genre checkForGenre(String genre) {
        return genreRepository.getByGenre(
                genre).orElseGet(() -> new Genre(0, genre));
    }

}
