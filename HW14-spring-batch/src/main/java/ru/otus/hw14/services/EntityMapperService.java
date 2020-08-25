package ru.otus.hw14.services;

import ru.otus.hw14.models.mongo.Author;

public interface EntityMapperService {
    Author mapAuthor(ru.otus.hw14.models.jpa.Author jpaAuthor);
}
