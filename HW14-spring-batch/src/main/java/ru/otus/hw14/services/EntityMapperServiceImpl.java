package ru.otus.hw14.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.hw14.models.mongo.Author;

@Service
public class EntityMapperServiceImpl implements EntityMapperService {
    @Override
    public Author mapAuthor(ru.otus.hw14.models.jpa.Author jpaAuthor) {
        return new Author(ObjectId.get().toString(), jpaAuthor.getFirstName(), jpaAuthor.getLastName());
    }
}
