package ru.otus.hw11.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11.models.Author;
import ru.otus.hw11.models.Book;
import ru.otus.hw11.models.Genre;
import ru.otus.hw11.repositories.AuthorRepository;
import ru.otus.hw11.repositories.BookRepository;
import ru.otus.hw11.repositories.CommentRepository;
import ru.otus.hw11.repositories.GenreRepository;

@RestController
public class BookRestController {

    private static final String NO_ID = null;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public BookRestController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/api/books")
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/api/books/identifiers")
    public Flux<Book> getAllIDs() {
        return bookRepository.getAllTitles();
    }

    @GetMapping("/api/books/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    //https://stackoverflow.com/questions/50058861/how-to-use-db-references-with-reactive-spring-data-mongodb
    @DeleteMapping("/api/books/{id}")
    public Flux<Void> deleteBookById(@PathVariable String id) {
        return bookRepository.deleteById(id).concatWith(commentRepository.deleteAllByBook_Id(id));
    }

    @PostMapping("/api/books") //400
//    @GetMapping("/api/booksss") //странное поведение поменял адрес урл чтобы не ругалось
    public Mono<String> createBook(
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre) {
        return Mono.zip(
                authorRepository.findByFirstNameAndLastName(firstName, lastName)
                        .switchIfEmpty(authorRepository.save(new Author(NO_ID, firstName, lastName))),
                genreRepository.findByGenre(genre)
                        .switchIfEmpty(genreRepository.save(new Genre(NO_ID, genre))))
                .flatMap(data -> bookRepository.save(new Book(NO_ID, title, data.getT1(), data.getT2())))
                .map(Book::getId);
    }
}
