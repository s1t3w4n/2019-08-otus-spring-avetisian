package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.app.QuestionPrintAdapter;

public abstract class Question {

    final QuestionPrintAdapter questionPrintAdapter;
    final String body;

    protected Question(QuestionPrintAdapter questionPrintAdapter, String body) {
        this.questionPrintAdapter = questionPrintAdapter;
        this.body = body;
    }

    public abstract String printQuestion();

    public abstract int rateTheAnswer(String answer);
}
