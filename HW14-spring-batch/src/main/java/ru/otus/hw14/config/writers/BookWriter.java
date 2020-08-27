package ru.otus.hw14.config.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import ru.otus.hw14.models.mongo.Book;
import ru.otus.hw14.repositories.mongo.AuthorMongoRepository;
import ru.otus.hw14.repositories.mongo.BookMongoRepository;
import ru.otus.hw14.repositories.mongo.GenreMongoRepository;

import java.util.List;

@Component
public class BookWriter implements ItemWriter<Book> {
    private final BookMongoRepository bookMongoRepository;
    private final AuthorMongoRepository authorMongoRepository;
    private final GenreMongoRepository genreMongoRepository;

    public BookWriter(BookMongoRepository bookMongoRepository,
                      AuthorMongoRepository authorMongoRepository,
                      GenreMongoRepository genreMongoRepository) {
        this.bookMongoRepository = bookMongoRepository;
        this.authorMongoRepository = authorMongoRepository;
        this.genreMongoRepository = genreMongoRepository;
    }

    @Override
    public void write(List<? extends Book> list) {
        list.forEach(book -> {
            book.setAuthor(authorMongoRepository.findByFirstNameAndLastName(book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName()).orElseThrow());
            book.setGenre(genreMongoRepository.findByGenre(book.getGenre().getGenre()).orElseThrow());
            bookMongoRepository.save(book);
        });
    }
}
