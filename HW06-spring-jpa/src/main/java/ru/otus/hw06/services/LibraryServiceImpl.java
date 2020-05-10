package ru.otus.hw06.services;

import org.springframework.stereotype.Service;
import ru.otus.hw06.repositories.BookRepositoryJPA;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookRepositoryJPA repository;

    public LibraryServiceImpl(BookRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public String createBook(String tittle, String firstName, String lastName, String genre) {
        return null;
    }

    @Override
    public String readById(long id) {
        return null;
    }

    @Override
    public String updateBook(long id, String tittle, String firstName, String lastName, String genre) {
        return null;
    }

    @Override
    public String deleteById(long id) {
        return null;
    }

    @Override
    public String readAllBooks() {
        return null;
    }
}
