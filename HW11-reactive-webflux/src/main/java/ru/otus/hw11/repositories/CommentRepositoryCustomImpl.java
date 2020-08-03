package ru.otus.hw11.repositories;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.hw11.models.Comment;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final ReactiveMongoTemplate mongoTemplate;

    public CommentRepositoryCustomImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Comment> findAllByBook_Id(String id) {
        Aggregation aggregation = newAggregation(Comment.class,
                match(Criteria.where("book.$id").is(id)));
        return mongoTemplate.aggregate(aggregation, Comment.class, Comment.class);
    }
}
