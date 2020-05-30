package ru.otus.hw08.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("genre")
public class Genre {
    @MongoId
    private long id;
    private String genre;
}
