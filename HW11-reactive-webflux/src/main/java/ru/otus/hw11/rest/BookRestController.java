package ru.otus.hw11.rest;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11.models.Author;
import ru.otus.hw11.models.Book;
import ru.otus.hw11.models.Genre;
import ru.otus.hw11.models.dto.BookDto;
import ru.otus.hw11.models.dto.BookWithIdDto;
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
    public Mono<Void> deleteBookById(@PathVariable String id) {
        return bookRepository.deleteById(id).then(commentRepository.deleteAllByBook_Id(id));
    }

    @PostMapping("/api/books")
    public Mono<String> createBook(BookDto dto) {
        return Mono.zip(
                authorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName())
                        .switchIfEmpty(authorRepository.save(new Author(NO_ID, dto.getFirstName(), dto.getLastName()))),
                genreRepository.findByGenre(dto.getGenre())
                        .switchIfEmpty(genreRepository.save(new Genre(NO_ID, dto.getGenre()))))
                .flatMap(data -> bookRepository.save(new Book(ObjectId.get().toString(), dto.getTitle(), data.getT1(), data.getT2())))
                .map(Book::getId);
    }

    @PutMapping("/api/books")
    public Mono<String> updateBook(BookWithIdDto dto) {
        return Mono.zip(
                authorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName())
                        .switchIfEmpty(authorRepository.save(new Author(NO_ID, dto.getFirstName(), dto.getLastName()))),
                genreRepository.findByGenre(dto.getGenre())
                        .switchIfEmpty(genreRepository.save(new Genre(NO_ID, dto.getGenre()))))
                .flatMap(data -> bookRepository.save(new Book(dto.getId(), dto.getTitle(), data.getT1(), data.getT2())))
                .map(Book::getId);
    }
}
