package ru.otus.hw14.services;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;
import ru.otus.hw14.repositories.jpa.AuthorJpaRepository;
import ru.otus.hw14.repositories.jpa.BookJpaRepository;
import ru.otus.hw14.repositories.jpa.CommentJpaRepository;
import ru.otus.hw14.repositories.jpa.GenreJpaRepository;
import ru.otus.hw14.repositories.mongo.AuthorMongoRepository;
import ru.otus.hw14.repositories.mongo.BookMongoRepository;
import ru.otus.hw14.repositories.mongo.CommentMongoRepository;
import ru.otus.hw14.repositories.mongo.GenreMongoRepository;

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
        return String.format(DATA_IN_JPA_REPOSITORY, MONGO) +
                NEW_LINE +
                "Book count: " + bookMongoRepository.count() + NEW_LINE +
                "Author count: " + authorMongoRepository.count() + NEW_LINE +
                "Genre count: " + genreMongoRepository.count() + NEW_LINE +
                "Comment count: " + commentMongoRepository.count();
    }

    @Override
    public void dbMigration() {
        try {
            jobOperator.start("myJob", "");
        } catch (Exception ignored) {
        }
    }
}
