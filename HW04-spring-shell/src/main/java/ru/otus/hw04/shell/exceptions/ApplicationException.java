package ru.otus.hw04.shell.exceptions;

abstract class ApplicationException extends Exception {
    ApplicationException(String message) {
        super(message);
    }
}
