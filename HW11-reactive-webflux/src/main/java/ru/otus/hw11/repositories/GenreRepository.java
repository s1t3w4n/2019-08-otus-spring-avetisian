package ru.otus.hw11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw11.models.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    //    Mono<Genre> findByGenre(String genre);
}
