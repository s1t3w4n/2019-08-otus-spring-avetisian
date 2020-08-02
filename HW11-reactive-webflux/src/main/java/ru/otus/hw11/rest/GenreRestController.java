package ru.otus.hw11.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.hw11.models.Genre;
import ru.otus.hw11.repositories.GenreRepository;

import java.util.List;

@RestController
public class GenreRestController {

    private final GenreRepository repository;

    public GenreRestController(GenreRepository repository) {
        this.repository = repository;
    }

    //https://stackoverflow.com/questions/48421597/returning-fluxstring-from-spring-webflux-returns-one-string-instead-of-array-o
    @GetMapping("/api/genre")
    public Mono<List<String>> getAllGenre() {
        return repository.findAll().map(Genre::getGenre).distinct().collectList();
    }

    @GetMapping("/api/genre/{id}")
    public Mono<Genre> getAllGenre(@PathVariable String id) {
        return repository.findById(id);
    }
}
