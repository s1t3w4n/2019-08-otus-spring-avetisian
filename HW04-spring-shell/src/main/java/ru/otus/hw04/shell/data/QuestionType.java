package ru.otus.hw04.shell.data;

public enum QuestionType {
    SIMPLE ("simple"),
    MULTIPLE ("multiple"),
    OPEN ("open");

    private final String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
