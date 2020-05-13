package ru.otus.hw06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.hw06.models.Book;
import ru.otus.hw06.models.Comment;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryJPAImpl implements CommentRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment addComment(String text, Book book) {
        Comment comment = new Comment(0, text, book);
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public List<Comment> getAllBookComments(long bookId) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("comments-entity-graph");
        TypedQuery<Comment> query = entityManager
                .createQuery("select c from Comment c join fetch c.book where c.book.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}
