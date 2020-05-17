package ru.otus.hw06.repositories;

import ru.otus.hw06.models.Author;

import java.util.Optional;

public interface AuthorRepositoryJPA {
    Author save(Author author);

    Optional<Author> getById(long id);

    Optional<Author> getByFullName(String firstName, String lastName);
}
