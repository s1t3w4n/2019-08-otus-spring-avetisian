package ru.otus.hw07.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07.models.Author;
import ru.otus.hw07.models.Book;
import ru.otus.hw07.models.Comment;
import ru.otus.hw07.models.Genre;
import ru.otus.hw07.repositories.AuthorRepository;
import ru.otus.hw07.repositories.BookRepository;
import ru.otus.hw07.repositories.CommentRepository;
import ru.otus.hw07.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final String NO_SUCH_ID = "There is no element with such id";
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
    public String createBook(String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(
                new Book(NO_ID,
                        tittle,
                        checkForAuthor(firstName, lastName),
                        checkForGenre(genre)))
                .toString() + "\nHas added to the library";
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
            return before + "\nHas updated into:\n" + book.toString();
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
            return book.get().toString() + "\nHas deleted";
        } else {
            return NO_SUCH_ID;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String readAllBooks() {
        StringBuilder builder = new StringBuilder("The library contains that books:");
        for (Book book : bookRepository.findAll()) {
            builder.append("\n");
            builder.append(book.toString());
        }
        return builder.toString();
    }

    @Transactional
    @Override
    public String leaveCommentToBook(long bookId, String text) {
        if (bookRepository.existsById(bookId)) {
            commentRepository.save(new Comment(NO_ID, text, bookRepository.findById(bookId).orElse(null)));
            final StringBuilder comments = new StringBuilder("Your commentary has added to:\n");
            final List<Comment> allBookComments = commentRepository.findByBookId(bookId);
            if (allBookComments.size() > 0) {
                comments.append(allBookComments.get(0).getBook().toString());
                for (int i = 0; i < allBookComments.size(); i++) {
                    comments.append("\n")
                            .append(i + 1)
                            .append(": ")
                            .append(allBookComments.get(i).getText());
                }
            } else {
                comments.append("\nNo comments for that book");
            }
            return comments.toString();
        } else {
            return NO_SUCH_ID;
        }
    }

    @Override
    public String getNoSuchIdMessage() {
        return NO_SUCH_ID;
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
