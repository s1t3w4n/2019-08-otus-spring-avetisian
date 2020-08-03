package ru.otus.hw11.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

//    @Transient
//    public static final String SEQUENCE_NAME = "books_sequence";

    @MongoId
    private String id;

    @Field("title")
    private String title;

    @Field("author")
    private Author author;

    @Field("genre")
    private Genre genre;
}
