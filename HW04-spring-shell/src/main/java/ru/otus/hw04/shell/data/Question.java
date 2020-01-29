package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.dao.QuestionPrintAdapter;

public interface Question {

    String printQuestion(QuestionPrintAdapter qpa);

    int rateTheAnswer(String answer);
}
