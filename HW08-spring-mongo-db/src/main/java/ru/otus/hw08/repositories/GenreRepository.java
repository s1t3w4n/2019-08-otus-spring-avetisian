package ru.otus.hw08.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw08.models.Genre;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, String> {
    Optional<Genre> findByGenre(String genre);
}
