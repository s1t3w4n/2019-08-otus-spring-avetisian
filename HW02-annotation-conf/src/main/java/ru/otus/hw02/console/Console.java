package ru.otus.hw02.console;

public interface Console {
    String read();

    void print(String information);

    void print(String bundle, Object... variables);
}
