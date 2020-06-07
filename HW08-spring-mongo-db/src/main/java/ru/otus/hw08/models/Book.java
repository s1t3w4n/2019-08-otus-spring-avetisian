package ru.otus.hw08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = Book.COLLECTION_NAME)
public class Book {
    public static final String COLLECTION_NAME = "books";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_GENRE = "genre";

    @MongoId
    private long id;

    @Field(Book.BOOK_TITLE)
    private String title;

    @Field(Book.BOOK_AUTHOR)
    private Author author;

    @Field(Book.BOOK_GENRE)
    private Genre genre;
}
