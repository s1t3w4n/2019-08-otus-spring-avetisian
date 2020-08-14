package ru.otus.hw11.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.hw11.models.Author;
import ru.otus.hw11.repositories.AuthorRepository;

import java.util.List;

@RestController
public class AuthorRestController {
    private final AuthorRepository repository;

    public AuthorRestController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/authors/first-names")
    public Mono<List<String>> getAllFirstNames() {
        return repository.getAllFirstNames().map(Author::getFirstName).distinct().collectList();
    }

    @GetMapping("api/authors/last-names")
    public Mono<List<String>> getAllLastNames() {
        return repository.getAllLastNames().map(Author::getLastName).distinct().collectList();
    }
}
