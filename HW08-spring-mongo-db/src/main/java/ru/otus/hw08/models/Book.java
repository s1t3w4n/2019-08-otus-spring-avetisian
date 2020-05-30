package ru.otus.hw08.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("books")
public class Book {
    @MongoId
    private long id;
    private String title;
    private Author author;
    private Genre genre;
}
