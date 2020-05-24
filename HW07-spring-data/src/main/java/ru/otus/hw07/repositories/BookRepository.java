package ru.otus.hw07.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw07.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @EntityGraph(value = "books-entity-graph")
    @Override
    Iterable<Book> findAll();
}
