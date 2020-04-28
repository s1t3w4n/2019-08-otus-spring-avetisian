package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Book;

import java.util.List;

public interface BookDao {
    //    boolean insert(Book book);
    List<Book> getAll();
    Book getById(long id);
}
