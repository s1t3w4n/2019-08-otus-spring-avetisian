package ru.otus.hw08.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw08.models.Book;

public interface BookRepository extends CrudRepository<Long, Book> {
}
