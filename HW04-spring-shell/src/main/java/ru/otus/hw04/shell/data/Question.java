package ru.otus.hw04.shell.data;

public abstract class Question {

    final String body;

    protected Question(String body) {
        this.body = body;
    }

    public abstract String[] getQuestionParts();

    public abstract int rateTheAnswer(String answer);

    public abstract QuestionType getType();
}
