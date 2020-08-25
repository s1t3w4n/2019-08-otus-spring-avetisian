package ru.otus.hw14.services;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.hw14.models.mongo.Author;
import ru.otus.hw14.models.mongo.Book;
import ru.otus.hw14.models.mongo.Genre;
import ru.otus.hw14.repositories.mongo.AuthorMongoRepository;
import ru.otus.hw14.repositories.mongo.BookMongoRepository;
import ru.otus.hw14.repositories.mongo.GenreMongoRepository;

@AllArgsConstructor
@Service
public class EntityMapperServiceImpl implements EntityMapperService {
    private final AuthorMongoRepository authorMongoRepository;
    private final GenreMongoRepository genreMongoRepository;
    private final BookMongoRepository bookMongoRepository;

    @Override
    public Author mapAuthor(ru.otus.hw14.models.jpa.Author author) {
        return new Author(ObjectId.get().toString(), author.getFirstName(), author.getLastName());
    }

    @Override
    public Genre mapGenre(ru.otus.hw14.models.jpa.Genre genre) {
        return new Genre(ObjectId.get().toString(), genre.getGenre());
    }

    @Override
    public Book mapBook(ru.otus.hw14.models.jpa.Book book) {
        final var mongoAuthor = authorMongoRepository
                .findByFirstNameAndLastName(
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName());
        final var mongoGenre = genreMongoRepository.findByGenre(book.getGenre().getGenre());
        return new Book(ObjectId.get().toString(),
                book.getTitle(),
                mongoAuthor.orElseThrow(),
                mongoGenre.orElseThrow());
    }
}
