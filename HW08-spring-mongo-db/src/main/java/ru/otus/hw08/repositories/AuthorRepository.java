package ru.otus.hw08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw08.models.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
    Optional<Author> getByFirstNameAndLastName(String firstName, String lastName);
}
