package ru.otus.hw10.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw10.models.Author;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.models.Comment;
import ru.otus.hw10.models.Genre;
import ru.otus.hw10.repositories.AuthorRepository;
import ru.otus.hw10.repositories.BookRepository;
import ru.otus.hw10.repositories.CommentRepository;
import ru.otus.hw10.repositories.GenreRepository;

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
        final Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            commentRepository.deleteCommentByBook(book.get());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> getAllBooksIDs() {
        return bookRepository.getAllBookIDs();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllFirstNames() {
        return authorRepository.getAllFirstNames();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllLastNames() {
        return authorRepository.getAllLastNames();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllGenre() {
        return genreRepository.getAllGenre();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getBookComments(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Transactional
    @Override
    public void leaveCommentToBook(Book book, String text) {
        commentRepository.save(new Comment(NO_ID, text, book));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> readAllComments() {
        return commentRepository.findAll();
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
