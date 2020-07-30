package ru.otus.hw11.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11.models.Book;
import ru.otus.hw11.repositories.BookRepository;

import java.util.List;
//import ru.otus.hw10.exceptions.NotFoundException;
//import ru.otus.hw10.models.Book;
//import ru.otus.hw10.service.LibraryService;

//import java.util.List;

@RestController
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
//    private final LibraryService service;
//
//    public BookRestController(LibraryService service) {
//        this.service = service;
//    }

    @GetMapping("api/books")
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("api/books/identifiers")
    public Flux<Book> getAllIDs() {
        return bookRepository.getAllIDs();
    }

    @GetMapping("api/books/{id}")
    public Mono<Book> getBookById(@PathVariable long id) {
        return bookRepository.findById(id);
    }
//
//    @DeleteMapping("api/books/{id}")
//    public Mono<Void> deleteBookById(@PathVariable long id) {
//        return bookRepository.deleteById(id);
//    }
//
//    @PostMapping("/api/books/")
//    public long createBook(
//            @RequestParam("title") String title,
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("genre") String genre) {
//        final Book book = service.createBook(title, firstName, lastName, genre);
//        return book.getId();
//    }
}
