package ru.otus.hw14.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.hw14.models.mongo.Author;
import ru.otus.hw14.models.mongo.Genre;

@Service
public class EntityMapperServiceImpl implements EntityMapperService {
    @Override
    public Author mapAuthor(ru.otus.hw14.models.jpa.Author author) {
        return new Author(ObjectId.get().toString(), author.getFirstName(), author.getLastName());
    }

    @Override
    public Genre mapGenre(ru.otus.hw14.models.jpa.Genre genre) {
        return new Genre(ObjectId.get().toString(), genre.getGenre());
    }
}
