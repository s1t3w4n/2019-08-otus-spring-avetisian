package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Genre;

public interface GenreDao {
    long insert(Genre genre);

    Genre getById(long id);

    Genre getByGenre(String genre);
}
