package ru.otus.hw04.shell.app;

import ru.otus.hw04.shell.data.QuestionType;

public interface QuestionPrinter {
    QuestionType getTYPE();
    String printQuestion(String... variables);
}
