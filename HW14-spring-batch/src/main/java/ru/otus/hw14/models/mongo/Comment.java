package ru.otus.hw14.models.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @MongoId
    private String id;
    @Field("text")
    private String text;

    @Field("book")
    @DBRef
    private Book book;
}
