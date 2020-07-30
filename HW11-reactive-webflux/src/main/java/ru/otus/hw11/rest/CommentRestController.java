package ru.otus.hw11.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.hw11.models.Comment;
import ru.otus.hw11.repositories.CommentRepository;

@RestController
public class CommentRestController {
    private final CommentRepository repository;

    public CommentRestController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/comments")
    public Flux<Comment> getAllComments() {
        return repository.findAll();
    }

    @GetMapping("api/comments/book/{id}")
    public Flux<Comment> getBookComments(@PathVariable long id) {
        return repository.findAllByBook_Id(id);
    }
}
