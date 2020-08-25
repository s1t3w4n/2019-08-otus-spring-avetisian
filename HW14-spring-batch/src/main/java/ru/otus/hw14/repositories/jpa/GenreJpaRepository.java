package ru.otus.hw14.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw14.models.jpa.Genre;

public interface GenreJpaRepository extends JpaRepository<Genre, Long> {
}
