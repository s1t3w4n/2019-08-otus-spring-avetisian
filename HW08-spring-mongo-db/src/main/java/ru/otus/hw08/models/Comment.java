package ru.otus.hw08.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("comments")
public class Comment {
    @MongoId
    private long id;
    private String text;
    private Book book;
}
