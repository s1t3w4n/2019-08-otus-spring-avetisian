package ru.otus.hw11.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.hw11.models.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentRepositoryCustom {

    @Query(value = "{'book.$id' : :#{#id}}", delete = true)
    void deleteAllByBook_Id(@Param("id") long id);

}
