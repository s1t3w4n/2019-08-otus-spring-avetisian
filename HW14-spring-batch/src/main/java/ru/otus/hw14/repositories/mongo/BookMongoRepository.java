package ru.otus.hw14.repositories.mongo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw14.models.mongo.Book;

public interface BookMongoRepository extends CrudRepository<Book, String> {
}
