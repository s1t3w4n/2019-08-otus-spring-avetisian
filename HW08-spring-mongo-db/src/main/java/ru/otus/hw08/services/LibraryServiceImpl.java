package ru.otus.hw08.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08.models.Author;
import ru.otus.hw08.models.Book;
import ru.otus.hw08.models.Comment;
import ru.otus.hw08.models.Genre;
import ru.otus.hw08.repositories.AuthorRepository;
import ru.otus.hw08.repositories.BookRepository;
import ru.otus.hw08.repositories.CommentRepository;
import ru.otus.hw08.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {
    private static final String NO_ID = null;
    public static final String BOOKS_COMMENTS = "Books comments:";
    public static final String NO_COMMENTS_FOR_THAT_BOOK = "\nNo comments for that book";
    public static final String HAS_ADDED_TO_THE_LIBRARY = "\nHas added to the library";
    public static final String HAS_UPDATED_INTO = "\nHas updated into:\n";
    public static final String HAS_DELETED = "\nHas deleted";
    public static final String THE_LIBRARY_CONTAINS_THAT_BOOKS = "The library contains that books:";
    public static final String YOUR_COMMENTARY_HAS_ADDED_TO = "Your commentary has added to:\n";
    public static final String NEXT_LINE = "\n";
    public static final String COLON_SPACE = ": ";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Transactional
    @Override
    public String createBook(String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(
                new Book(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME),
                        tittle,
                        checkForAuthor(firstName, lastName),
                        checkForGenre(genre)))
                .toString() + HAS_ADDED_TO_THE_LIBRARY;
    }

    @Transactional(readOnly = true)
    @Override
    public String readById(long id) {
        return bookRepository.findById(id).map(Book::toString).orElse(NO_SUCH_ID);
    }

    @Transactional
    @Override
    public String updateBook(long id, String tittle, String firstName, String lastName, String genre) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        String before;
        if (optionalBook.isPresent()) {
            before = optionalBook.get().toString();
            Book book = bookRepository.save(
                    new Book(id, tittle, checkForAuthor(firstName, lastName), checkForGenre(genre)));
            return before + HAS_UPDATED_INTO + book.toString();
        } else {
            return NO_SUCH_ID;
        }
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return book.get().toString() + HAS_DELETED;
        } else {
            return NO_SUCH_ID;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String readAllBooks() {
        StringBuilder builder = new StringBuilder(THE_LIBRARY_CONTAINS_THAT_BOOKS);
        for (Book book : bookRepository.findAll()) {
            builder.append(NEXT_LINE);
            builder.append(book.toString());
        }
        return builder.toString();
    }

    @Transactional
    @Override
    public String leaveCommentToBook(long bookId, String text) {
        if (bookRepository.existsById(bookId)) {
            commentRepository.save(new Comment(NO_ID, text, bookRepository.findById(bookId).orElse(null)));
            final StringBuilder comments = new StringBuilder(YOUR_COMMENTARY_HAS_ADDED_TO);
            final List<Comment> allBookComments = commentRepository.findAllByBook_Id(bookId);
            if (allBookComments.size() > 0) {
                comments.append(allBookComments.get(0).getBook().toString());
                for (int i = 0; i < allBookComments.size(); i++) {
                    comments.append(NEXT_LINE)
                            .append(i + 1)
                            .append(COLON_SPACE)
                            .append(allBookComments.get(i).getText());
                }
            } else {
                comments.append(NO_COMMENTS_FOR_THAT_BOOK);
            }
            return comments.toString();
        } else {
            return NO_SUCH_ID;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String readAllComments() {
        StringBuilder stringBuilder = new StringBuilder(BOOKS_COMMENTS);
        for (Comment comment : commentRepository.findAll()) {
            stringBuilder.append(NEXT_LINE);
            stringBuilder.append(comment);
        }
        return stringBuilder.toString();
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
