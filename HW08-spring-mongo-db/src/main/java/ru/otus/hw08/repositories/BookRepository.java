package ru.otus.hw08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw08.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
