package ru.otus.hw04.shell.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenQuestion implements Question {

    private final String body;
    private final Set<String> options;

    private static final int RIGHT_OPTIONS_QUANTITY = 1;
    private static final QuestionType type = QuestionType.OPEN;

    public OpenQuestion(String body, List<String> correctOptions) {
        this.body = body;
        options = new HashSet<>(correctOptions);
    }

}
