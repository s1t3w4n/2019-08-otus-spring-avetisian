package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Book;

import java.util.List;

public interface BookDao {
    long insert(Book book);

    Book getById(long id);

    void update(Book book);

    void deleteById(long id);

    List<Book> getAll();
}
