package ru.otus.hw08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document(collection = Genre.COLLECTION_NAME)
public class Genre {
    public static final String COLLECTION_NAME = "genre";
    public static final String GENRE_GENRE = "genre";

    @Field(Genre.GENRE_GENRE)
    private String genre;
}