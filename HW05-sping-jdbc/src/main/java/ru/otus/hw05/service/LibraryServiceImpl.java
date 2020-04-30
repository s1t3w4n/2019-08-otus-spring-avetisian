package ru.otus.hw05.service;

import org.springframework.stereotype.Service;
import ru.otus.hw05.dao.AuthorDao;
import ru.otus.hw05.dao.BookDao;
import ru.otus.hw05.dao.GenreDao;
import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Book;
import ru.otus.hw05.domain.Genre;

import java.util.Objects;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    private static final String NO_SUCH_ID = "There is no element with such id";

    public LibraryServiceImpl(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    @Override
    public String createBook(String tittle, String firstName, String lastName, String genre) {
        return bookDao.getById(bookDao.insert(
                new Book(tittle,
                        checkAuthor(firstName, lastName),
                        checkGenre(genre)))).toString() +
                "\nHas added to the library";
    }

    @Override
    public String readById(long id) {
        Book book = bookDao.getById(id);
        return Objects.nonNull(book) ? book.toString() : NO_SUCH_ID;
    }

    @Override
    public String updateBook(long id, String tittle, String firstName, String lastName, String genre) {
        Book book = bookDao.getById(id);
        if (Objects.nonNull(book)) {
            bookDao.update(new Book(
                    id,
                    tittle,
                    checkAuthor(firstName, lastName),
                    checkGenre(genre)
            ));
            return book.toString() + "\nHas updated into:\n" + bookDao.getById(id);
        } else {
            return NO_SUCH_ID;
        }
    }

    @Override
    public String deleteById(long id) {
        Book book = bookDao.getById(id);
        if (Objects.nonNull(book)) {
            bookDao.deleteById(id);
            return book.toString() + "\nHas deleted";
        } else {
            return NO_SUCH_ID;
        }
    }

    @Override
    public String readAllBooks() {
        StringBuilder builder = new StringBuilder("The library contains that books:\n");
        for (Book book : bookDao.getAll()) {
            builder.append(book.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    private Genre checkGenre(String genre) {
        Genre bookGenre = genreDao.getByGenre(genre);
        if (Objects.isNull(bookGenre)) {
            bookGenre = genreDao.getById(genreDao.insert(new Genre(genre)));
        }
        return bookGenre;
    }

    private Author checkAuthor(String firstName, String lastName) {
        Author bookAuthor = authorDao.getByName(firstName, lastName);
        if (Objects.isNull(bookAuthor)) {
            bookAuthor = authorDao.getById(authorDao.insert(new Author(firstName, lastName)));
        }
        return bookAuthor;
    }
}
