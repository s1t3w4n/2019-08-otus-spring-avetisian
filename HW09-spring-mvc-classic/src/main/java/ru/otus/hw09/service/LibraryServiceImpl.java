package ru.otus.hw09.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09.models.Author;
import ru.otus.hw09.models.Book;
import ru.otus.hw09.models.Comment;
import ru.otus.hw09.models.Genre;
import ru.otus.hw09.repositories.AuthorRepository;
import ru.otus.hw09.repositories.BookRepository;
import ru.otus.hw09.repositories.CommentRepository;
import ru.otus.hw09.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final long NO_ID = 0;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public LibraryServiceImpl(BookRepository bookRepository,
                              AuthorRepository authorRepository,
                              GenreRepository genreRepository,
                              CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public Book createBook(String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(
                new Book(NO_ID,
                        tittle,
                        checkForAuthor(firstName, lastName),
                        checkForGenre(genre)));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> readById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @Override
    public Book updateBook(long id, String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(
                new Book(id, tittle, checkForAuthor(firstName, lastName), checkForGenre(genre)));
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getBookComments(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public String leaveCommentToBook(long bookId, String text) {
        return null;
    }

    @Override
    public String readAllComments() {
        return null;
    }

    private Author checkForAuthor(String firstName, String lastName) {
        return authorRepository
                .getByFirstNameAndLastName(firstName, lastName)
                .orElseGet(() -> authorRepository.save(new Author(NO_ID, firstName, lastName)));
    }

    private Genre checkForGenre(String genre) {
        return genreRepository
                .findByGenre(genre)
                .orElseGet(() -> genreRepository.save(new Genre(NO_ID, genre)));
    }
}
