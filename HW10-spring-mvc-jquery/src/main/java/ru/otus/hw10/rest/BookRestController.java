package ru.otus.hw10.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10.exceptions.NotFoundException;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.service.LibraryService;

import java.util.List;

@RestController
public class BookRestController {

    private final LibraryService service;

    public BookRestController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("api/books")
    public List<Book> getAllBooks() {
        return service.readAllBooks();
    }

    @GetMapping("api/books/identifiers")
    public List<Long> getAllIDs() {
        return service.getAllBooksIDs();
    }

    @GetMapping("api/books/{id}")
    public Book getBookById(@PathVariable long id) {
        return service.readById(id).orElseThrow(NotFoundException::new);
    }
}
