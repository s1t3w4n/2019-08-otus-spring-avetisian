package ru.otus.hw10.exceptions;

public class NotFoundException extends ApplicationException{

    private static final String MESSAGE = "There is no book with such id";

    public NotFoundException() {
        super(MESSAGE);
    }
}
