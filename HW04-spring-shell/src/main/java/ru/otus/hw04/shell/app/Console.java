package ru.otus.hw04.shell.app;


public interface Console {
    void print(String information);

    void print(String bundle, Object... variables);
}
