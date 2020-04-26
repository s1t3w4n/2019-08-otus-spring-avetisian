package ru.otus.hw05.domain;

public class Book {
    private final long bookId;
    private final String title;
    private final Author author;
    private final Genre genre;

    public Book(long bookId, String title, Author author, Genre genre) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author=" + author.getFirstName() + " "+ author.getLastName() +
                ", genre=" + genre.getGenre() +
                '}';
    }
}
