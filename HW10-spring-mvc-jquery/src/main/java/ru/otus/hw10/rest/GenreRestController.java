package ru.otus.hw10.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10.service.LibraryService;

import java.util.List;

@RestController
public class GenreRestController {
    private final LibraryService service;

    public GenreRestController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("api/genre")
    public List<String> getAllGenre() {
        return service.getAllGenre();
    }
}
