package ru.otus.hw13.exceptions;

public class NotFoundException extends ApplicationException{

    private static final String MESSAGE = "There is no book with such id";

    public NotFoundException() {
        super(MESSAGE);
    }
}
