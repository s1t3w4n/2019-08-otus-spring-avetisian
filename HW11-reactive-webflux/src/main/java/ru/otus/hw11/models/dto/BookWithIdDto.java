package ru.otus.hw11.models.dto;

import lombok.Data;

@Data
public class BookWithIdDto {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String genre;
}
