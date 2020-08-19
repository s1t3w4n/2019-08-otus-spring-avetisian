package ru.otus.hw12.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw12.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @EntityGraph(value = "books-entity-graph")
    @Override
    List<Book> findAll();

    @Query("select b.id from Book b")
    List<Long> getAllBookIDs();
}
