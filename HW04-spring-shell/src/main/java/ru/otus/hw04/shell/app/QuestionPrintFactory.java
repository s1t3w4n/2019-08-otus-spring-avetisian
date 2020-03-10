package ru.otus.hw04.shell.app;

import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.exceptions.CantPrintQuestionException;

public interface QuestionPrintFactory {
    String printQuestion(Question question) throws CantPrintQuestionException;
}
