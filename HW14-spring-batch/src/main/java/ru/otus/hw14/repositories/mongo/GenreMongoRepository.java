package ru.otus.hw14.repositories.mongo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw14.models.mongo.Genre;

public interface GenreMongoRepository extends CrudRepository<Genre, String> {
}
