package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Author;

public interface AuthorDao {
    long insert(Author author);

    Author getById(long id);

    Author getByFullName(String firstName, String lastName);
}
