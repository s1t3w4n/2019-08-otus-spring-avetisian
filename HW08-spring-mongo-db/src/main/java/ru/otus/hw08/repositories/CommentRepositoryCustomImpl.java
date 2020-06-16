package ru.otus.hw08.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import ru.otus.hw08.models.Comment;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public CommentRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Comment> findAllByBook_Id(long id) {
        Aggregation aggregation = newAggregation(Comment.class,
                match(Criteria.where("book.$id").is(id)));
        return mongoTemplate.aggregate(aggregation, Comment.class, Comment.class).getMappedResults();
    }
}
