package ru.otus.hw05.domain;

public class Author {
    private final long authorId;
    private final String firstName;
    private final String lastName;

    public Author(long authorId, String firstName, String lastName) {
        this.authorId = authorId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Author(String firstName, String lastName) {
        this.authorId = -1;
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
