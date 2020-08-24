package ru.otus.hw14.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw14.models.jpa.Genre;

public interface GenreJpaRepository extends CrudRepository<Genre, Long> {
}
