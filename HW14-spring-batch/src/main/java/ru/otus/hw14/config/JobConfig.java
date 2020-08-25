package ru.otus.hw14.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw14.models.jpa.Author;
import ru.otus.hw14.models.jpa.Book;
import ru.otus.hw14.models.jpa.Comment;
import ru.otus.hw14.models.jpa.Genre;
import ru.otus.hw14.repositories.jpa.AuthorJpaRepository;
import ru.otus.hw14.repositories.jpa.BookJpaRepository;
import ru.otus.hw14.repositories.jpa.CommentJpaRepository;
import ru.otus.hw14.repositories.jpa.GenreJpaRepository;
import ru.otus.hw14.services.EntityMapperService;

import java.util.HashMap;

@SuppressWarnings("SpringFacetCodeInspection")
@AllArgsConstructor
@Configuration
public class JobConfig {
    public static final String MY_JOB = "myJob";
    public static final String FIND_ALL = "findAll";
    public static final String COMMENT_STEP = "commentStep";
    public static final String BOOK_STEP = "bookStep";
    public static final String GENRE_STEP = "genreStep";
    public static final String AUTHORS_STEP = "authorsStep";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;

    private final AuthorJpaRepository authorJpaRepository;
    private final GenreJpaRepository genreJpaRepository;
    private final BookJpaRepository bookJpaRepository;
    private final CommentJpaRepository commentJpaRepository;

    private final EntityMapperService entityMapperService;

    @StepScope
    @Bean
    public RepositoryItemReader<Author> authorReader() {
        return new RepositoryItemReaderBuilder<Author>()
                .name("authorReader")
                .sorts(new HashMap<>())
                .repository(authorJpaRepository)
                .methodName(FIND_ALL)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Genre> genreReader() {
        return new RepositoryItemReaderBuilder<Genre>()
                .name("genreReader")
                .sorts(new HashMap<>())
                .repository(genreJpaRepository)
                .methodName(FIND_ALL)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Book> bookReader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("booksReader")
                .sorts(new HashMap<>())
                .repository(bookJpaRepository)
                .methodName(FIND_ALL)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Comment> commentReader() {
        return new RepositoryItemReaderBuilder<Comment>()
                .name("commentReader")
                .sorts(new HashMap<>())
                .repository(commentJpaRepository)
                .methodName(FIND_ALL)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, ru.otus.hw14.models.mongo.Author> authorProcessor() {
        return entityMapperService::mapAuthor;
    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, ru.otus.hw14.models.mongo.Genre> genreProcessor() {
        return entityMapperService::mapGenre;
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, ru.otus.hw14.models.mongo.Book> bookProcessor() {
        return entityMapperService::mapBook;
    }

    @StepScope
    @Bean
    public ItemProcessor<Comment, ru.otus.hw14.models.mongo.Comment> commentProcessor() {
        return entityMapperService::mapComment;
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.hw14.models.mongo.Author> authorWriter() {
        return new MongoItemWriterBuilder<ru.otus.hw14.models.mongo.Author>()
                .collection("authors")
                .template(mongoTemplate)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.hw14.models.mongo.Genre> genreWriter() {
        return new MongoItemWriterBuilder<ru.otus.hw14.models.mongo.Genre>()
                .collection("genre")
                .template(mongoTemplate)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.hw14.models.mongo.Book> bookWriter() {
        return new MongoItemWriterBuilder<ru.otus.hw14.models.mongo.Book>()
                .collection("books")
                .template(mongoTemplate)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.hw14.models.mongo.Comment> commentWriter() {
        return new MongoItemWriterBuilder<ru.otus.hw14.models.mongo.Comment>()
                .collection("comments")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step authorStep(ItemReader<Author> reader,
                           ItemProcessor<Author, ru.otus.hw14.models.mongo.Author> itemProcessor,
                           ItemWriter<ru.otus.hw14.models.mongo.Author> writer) {
        return stepBuilderFactory.get(AUTHORS_STEP)
                .<Author, ru.otus.hw14.models.mongo.Author>chunk(3)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step genreStep(ItemReader<Genre> reader,
                          ItemProcessor<Genre, ru.otus.hw14.models.mongo.Genre> itemProcessor,
                          ItemWriter<ru.otus.hw14.models.mongo.Genre> writer) {
        return stepBuilderFactory.get(GENRE_STEP)
                .<Genre, ru.otus.hw14.models.mongo.Genre>chunk(3)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step bookStep(ItemReader<Book> reader,
                         ItemProcessor<Book, ru.otus.hw14.models.mongo.Book> itemProcessor,
                         ItemWriter<ru.otus.hw14.models.mongo.Book> writer) {
        return stepBuilderFactory.get(BOOK_STEP)
                .<Book, ru.otus.hw14.models.mongo.Book>chunk(3)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step commentStep(ItemReader<Comment> reader,
                            ItemProcessor<Comment, ru.otus.hw14.models.mongo.Comment> itemProcessor,
                            ItemWriter<ru.otus.hw14.models.mongo.Comment> writer) {
        return stepBuilderFactory.get(COMMENT_STEP)
                .<Comment, ru.otus.hw14.models.mongo.Comment>chunk(3)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get(MY_JOB)
                .incrementer(new RunIdIncrementer())
                .flow(authorStep(authorReader(), authorProcessor(), authorWriter()))
                .next(genreStep(genreReader(), genreProcessor(), genreWriter()))
                .next(bookStep(bookReader(), bookProcessor(), bookWriter()))
                .next(commentStep(commentReader(), commentProcessor(), commentWriter()))
                .end()
                .build();
    }
}
