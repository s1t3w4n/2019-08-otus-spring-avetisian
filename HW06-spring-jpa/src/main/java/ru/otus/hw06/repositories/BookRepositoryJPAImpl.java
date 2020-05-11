package ru.otus.hw06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06.models.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryJPAImpl implements BookRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("books-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book save(Book book) {
        if (book.getAuthor().getId() > 0) {
            book.setAuthor(entityManager.merge(book.getAuthor()));
        }
        if (book.getGenre().getId() > 0) {
            book.setGenre(entityManager.merge(book.getGenre()));
        }
        if (book.getId() <= 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

}
