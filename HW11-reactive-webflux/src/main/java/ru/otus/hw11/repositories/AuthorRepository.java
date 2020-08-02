package ru.otus.hw11.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.hw11.models.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    @Query(value = "{}", fields = "{ 'firstName': 1 , '_id': 0}")
    Flux<Author> getAllFirstNames();

    @Query(value = "{}", fields = "{ 'lastName': 1 , '_id': 0}")
    Flux<Author> getAllLastNames();
}
