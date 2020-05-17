package ru.otus.hw06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.hw06.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AuthorRepositoryJPAImpl implements AuthorRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Optional<Author> getByFullName(String firstName, String lastName) {
        TypedQuery<Author> query =
                entityManager.createQuery(
                        "select a from Author a where a.firstName = :firstName and a.lastName = :lastName",
                        Author.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
