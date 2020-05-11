package ru.otus.hw06.repositories;

import ru.otus.hw06.models.Genre;

import java.util.Optional;

public interface GenreRepositoryJPA {
    Genre save(Genre genre);

    Optional<Genre> getById(long id);

    Optional<Genre> getByGenre(String genre);
}
