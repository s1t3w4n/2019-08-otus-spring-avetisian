package ru.otus.hw14.repositories.mongo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw14.models.mongo.Genre;

import java.util.Optional;

public interface GenreMongoRepository extends CrudRepository<Genre, String> {
    Optional<Genre> findByGenre(String genre);
}
