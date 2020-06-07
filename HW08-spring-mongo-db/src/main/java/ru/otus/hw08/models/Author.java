package ru.otus.hw08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = Author.COLLECTION_NAME)
public class Author {
    public static final String COLLECTION_NAME = "authors";
    public static final String AUTHOR_FIRST_NAME = "firstName";
    public static final String AUTHOR_LAST_NAME = "lastName";

    @MongoId
    private String id;

    @Field(Author.AUTHOR_FIRST_NAME)
    private String firstName;

    @Field(Author.AUTHOR_LAST_NAME)
    private String lastName;
}
