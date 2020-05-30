package ru.otus.hw08.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("authors")
public class Author {
    @MongoId
    private long id;
    private String firstName;
    private String lastName;
}
