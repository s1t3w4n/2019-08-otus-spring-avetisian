package ru.otus.hw10.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw10.models.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> getByFirstNameAndLastName(String firstName, String lastName);

    @Query("select distinct a.firstName from Author a")
    List<String> getAllFirstNames();

    @Query("select distinct a.lastName from Author a")
    List<String> getAllLastNames();
}
