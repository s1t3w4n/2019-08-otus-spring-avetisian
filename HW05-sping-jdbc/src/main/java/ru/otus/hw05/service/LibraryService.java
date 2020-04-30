package ru.otus.hw05.service;


public interface LibraryService {
    String createBook(String tittle, String firstName, String lastName, String genre);

    String readById(long id);

    String updateBook(long id, String tittle, String firstName, String lastName, String genre);

    String deleteById(long id);

    String readAllBooks();
}
