package ru.otus.hw05.domain;

public class Author {
    private final long authorId;
    private final String lastName;
    private final String firstName;

    public Author(long authorId, String lastName, String firstName) {
        this.authorId = authorId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
