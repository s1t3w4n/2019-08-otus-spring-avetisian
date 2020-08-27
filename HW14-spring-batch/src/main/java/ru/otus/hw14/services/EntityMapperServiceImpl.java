package ru.otus.hw14.services;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.hw14.models.mongo.Author;
import ru.otus.hw14.models.mongo.Book;
import ru.otus.hw14.models.mongo.Comment;
import ru.otus.hw14.models.mongo.Genre;
import ru.otus.hw14.repositories.mongo.BookMongoRepository;

@AllArgsConstructor
@Service
public class EntityMapperServiceImpl implements EntityMapperService {
    private final BookMongoRepository bookMongoRepository;

    private final IdHolderService idHolderService;

    @Override
    public Author mapAuthor(ru.otus.hw14.models.jpa.Author author) {
        return new Author(author.getFirstName(), author.getLastName());
    }

    @Override
    public Genre mapGenre(ru.otus.hw14.models.jpa.Genre genre) {
        return new Genre(genre.getGenre());
    }

    @Override
    public Book mapBook(ru.otus.hw14.models.jpa.Book jpaBook) {
        final var mongoBook =
                new Book(ObjectId.get().toString(),
                        jpaBook.getTitle(),
                        mapAuthor(jpaBook.getAuthor()),
                        mapGenre(jpaBook.getGenre()));
        idHolderService.addBookMongoID(jpaBook.getId(), mongoBook.getId());
        return mongoBook;
    }

    @Override
    public Comment mapComment(ru.otus.hw14.models.jpa.Comment comment) {
        final var byId = bookMongoRepository.findById(idHolderService.getBookMongoId(comment.getBook().getId()));
        return new Comment(comment.getText(), byId.orElseThrow());
    }
}
