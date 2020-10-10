package ru.otus.hw16.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.otus.hw16.models.Book;

import java.util.List;

@RepositoryRestResource(path = "book")
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @EntityGraph(value = "books-entity-graph")
    @Override
    List<Book> findAll();

    @Query("select b.id from Book b")
    List<Long> getAllBookIDs();
}
