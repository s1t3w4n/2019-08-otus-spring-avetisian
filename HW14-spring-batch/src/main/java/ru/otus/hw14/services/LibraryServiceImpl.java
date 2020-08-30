package ru.otus.hw14.services;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;
import ru.otus.hw14.models.mongo.Book;
import ru.otus.hw14.models.mongo.Comment;
import ru.otus.hw14.repositories.jpa.AuthorJpaRepository;
import ru.otus.hw14.repositories.jpa.BookJpaRepository;
import ru.otus.hw14.repositories.jpa.CommentJpaRepository;
import ru.otus.hw14.repositories.jpa.GenreJpaRepository;
import ru.otus.hw14.repositories.mongo.AuthorMongoRepository;
import ru.otus.hw14.repositories.mongo.BookMongoRepository;
import ru.otus.hw14.repositories.mongo.CommentMongoRepository;
import ru.otus.hw14.repositories.mongo.GenreMongoRepository;

import static ru.otus.hw14.config.JobConfig.*;

@AllArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    public static final String DATA_IN_JPA_REPOSITORY = "Data in %s repository: ";
    public static final String NEW_LINE = "\n";
    public static final String JPA = "JPA";
    public static final String MONGO = "MONGO";

    private final BookJpaRepository bookJpaRepository;
    private final AuthorJpaRepository authorJpaRepository;
    private final GenreJpaRepository genreJpaRepository;
    private final CommentJpaRepository commentJpaRepository;

    private final BookMongoRepository bookMongoRepository;
    private final AuthorMongoRepository authorMongoRepository;
    private final GenreMongoRepository genreMongoRepository;
    private final CommentMongoRepository commentMongoRepository;

    private final JobOperator jobOperator;

    @Override
    public String getJPAData() {
        return String.format(DATA_IN_JPA_REPOSITORY, JPA) +
                NEW_LINE +
                "Book count: " + bookJpaRepository.count() + NEW_LINE +
                "Author count: " + authorJpaRepository.count() + NEW_LINE +
                "Genre count: " + genreJpaRepository.count() + NEW_LINE +
                "Comment count: " + commentJpaRepository.count();
    }

    @Override
    public String getMongoData() {
        final var stringBuilder = new StringBuilder(String.format(DATA_IN_JPA_REPOSITORY, MONGO) +
                NEW_LINE +
                "Book count: " + bookMongoRepository.count() + NEW_LINE +
                "Author count: " + authorMongoRepository.count() + NEW_LINE +
                "Genre count: " + genreMongoRepository.count() + NEW_LINE +
                "Comment count: " + commentMongoRepository.count());
        if (bookMongoRepository.count() != 0) {
            for (Book book : bookMongoRepository.findAll()) {
                stringBuilder.append(NEW_LINE).append(book);
            }
            for (Comment comment : commentMongoRepository.findAll()) {
                stringBuilder.append(NEW_LINE).append(comment);
            }
        } else {
            stringBuilder.append(NEW_LINE).append("DB is Empty");
        }
        return stringBuilder.toString();
    }

    @Override
    public void dbMigration() {
        try {
            clean();
            jobOperator.startNextInstance(MY_JOB);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void clean() {
        commentMongoRepository.deleteAll();
        bookMongoRepository.deleteAll();
        genreMongoRepository.deleteAll();
        authorMongoRepository.deleteAll();
    }
}
