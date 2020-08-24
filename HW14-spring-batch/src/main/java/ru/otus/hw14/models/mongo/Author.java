package ru.otus.hw14.models.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

    @MongoId
    private String id;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;
}
