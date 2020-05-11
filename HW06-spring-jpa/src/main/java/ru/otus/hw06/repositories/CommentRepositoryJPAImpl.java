package ru.otus.hw06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class CommentRepositoryJPAImpl implements CommentRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment addComment(String text) {
        Comment comment = new Comment(0, text);
        entityManager.persist(comment);
        return comment;
    }
}
