package ru.otus.hw08.services;

public interface LibraryService {

    String NO_SUCH_ID = "There is no element with such id";

    String createBook(String tittle, String firstName, String lastName, String genre);

    String readById(long id);

    String updateBook(long id, String tittle, String firstName, String lastName, String genre);

    String deleteById(long id);

    String readAllBooks();

    String leaveCommentToBook(long bookId, String text);

    static String getNoSuchIdMessage() {
        return NO_SUCH_ID;
    }
}
