package ru.otus.hw11.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.hw11.models.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    @Query(value = "{}", fields = "{ '_id': 1, 'title': 1 }")
    Flux<Book> getAllTitles();
}
