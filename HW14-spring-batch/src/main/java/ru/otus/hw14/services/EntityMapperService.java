package ru.otus.hw14.services;

import ru.otus.hw14.models.mongo.Author;
import ru.otus.hw14.models.mongo.Book;
import ru.otus.hw14.models.mongo.Genre;

public interface EntityMapperService {
    Author mapAuthor(ru.otus.hw14.models.jpa.Author jpaAuthor);

    Genre mapGenre(ru.otus.hw14.models.jpa.Genre genre);

    Book mapBook(ru.otus.hw14.models.jpa.Book book);
}
