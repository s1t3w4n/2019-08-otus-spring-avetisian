package ru.otus.hw10.rest;

import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("api/books/{id}")
    public void deleteBookById(@PathVariable long id) {
        service.deleteById(id);
    }

    @PostMapping("/api/books/create")
    public long createBook(
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre) {
        final Book book = service.createBook(title, firstName, lastName, genre);
        return book.getId();
    }
}
