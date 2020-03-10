package ru.otus.hw04.shell.app;

public interface PrintAdapter {
    String print(String bundle, Object... variables);
    String print(String bundle);
}
