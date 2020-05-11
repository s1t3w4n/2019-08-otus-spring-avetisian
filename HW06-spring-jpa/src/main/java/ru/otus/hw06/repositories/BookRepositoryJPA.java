package ru.otus.hw06.repositories;

import ru.otus.hw06.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJPA {
    Optional<Book> findById(long id);

    List<Book> getAll();

    void deleteById(long id);

    Book save(Book book);
}
