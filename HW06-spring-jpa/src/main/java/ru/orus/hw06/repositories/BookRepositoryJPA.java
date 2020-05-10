package ru.orus.hw06.repositories;

import ru.orus.hw06.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJPA {
    Optional<Book> findById(long id);
    List<Book> getAll();
}
