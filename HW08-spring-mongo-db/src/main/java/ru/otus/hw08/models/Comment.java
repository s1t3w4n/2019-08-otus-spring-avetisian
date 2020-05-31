package ru.otus.hw08.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("comments")
public class Comment {
    private String text;
    private Book book;
}
