package ru.otus.hw06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class GenreRepositoryJPAImpl implements GenreRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() <= 0) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> getByGenre(String genre) {
        TypedQuery<Genre> query =
                entityManager.createQuery("select g from Genre g where g.genre = :genre", Genre.class);
        query.setParameter("genre", genre);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (
                NoResultException e) {
            return Optional.empty();
        }
    }
}
