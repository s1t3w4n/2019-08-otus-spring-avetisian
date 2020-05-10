package ru.orus.hw06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.orus.hw06.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }
}
