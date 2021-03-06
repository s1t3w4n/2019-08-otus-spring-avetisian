package ru.otus.hw08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw08.models.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, String> {
    Optional<Genre> findByGenre(String genre);
}
