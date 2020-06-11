package ru.otus.hw08.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.hw08.models.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

    @Query("{'book.$id' : :#{#id}}")
    List<Comment> findAllByBook_Id(@Param("id") long id);

    void deleteAllByBook_Id(long id);

}
