package ru.otus.hw04.shell.app;

public interface QuestionPrintAdapter {
    String print(String[] questionParts);
    String print(String bundle, Object... variables);
    String print(String bundle);
}
