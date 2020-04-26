package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Book;

public interface BookDao {
    //    boolean insert(Book book);
    Book getById(long id);
}
