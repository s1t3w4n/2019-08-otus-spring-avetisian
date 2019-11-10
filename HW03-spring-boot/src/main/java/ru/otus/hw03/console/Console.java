package ru.otus.hw03.console;

public interface Console {
    String read();

    void print(String information);

    void print(String bundle, Object... variables);
}
