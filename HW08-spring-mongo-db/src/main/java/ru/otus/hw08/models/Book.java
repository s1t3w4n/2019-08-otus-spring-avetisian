package ru.otus.hw08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @MongoId
    private long id;

    @Field("title")
    private String title;

    @Field("author")
    private Author author;

    @Field("genre")
    private Genre genre;
}
