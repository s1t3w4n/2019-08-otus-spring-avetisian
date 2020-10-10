package ru.otus.hw16.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.otus.hw16.models.Genre;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "genre")
@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByGenre(String genre);

    @Query("select distinct g.genre from Genre g")
    List<String> getAllGenre();
}
