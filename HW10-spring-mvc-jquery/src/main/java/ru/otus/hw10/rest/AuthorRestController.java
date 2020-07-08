package ru.otus.hw10.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10.service.LibraryService;

import java.util.List;

@RestController
public class AuthorRestController {

    private final LibraryService service;

    public AuthorRestController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("/api/authors/first-names")
    public List<String> getAllFirstNames() {
        return service.getAllFirstNames();
    }

    @GetMapping("/api/authors/last-names")
    public List<String> getAllLastNames() {
        return service.getAllLastNames();
    }
}
