package ru.otus.hw03.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultipleQuestion implements Question {

    private final String body;
    private final Map<String,Boolean> options;
    private final int correctOptionsQuantity;

    private static final QuestionType type = QuestionType.MULTIPLE;

    public MultipleQuestion(String body, List<String> correctOptions, List<String> wrongOptions) {
        this.body = body;
        options = new HashMap<>();
        for (String correctOption : correctOptions) {
            options.put(correctOption, true);
        }

        correctOptionsQuantity = options.size();

        for (String wrongOption : wrongOptions) {
            if (!options.keySet().contains(wrongOption)) {
                options.put(wrongOption,false);
            }
        }
    }

    @Override
    public String getQuestionText() {
        return body;
    }

    @Override
    public Set<String> getOptions() {
        return options.keySet();
    }

    @Override
    public boolean checkAnswer(String answer) {
        return options.getOrDefault(answer, false);
    }

    @Override
    public int getQuantityOfRightOptions() {
        return correctOptionsQuantity;
    }

    @Override
    public QuestionType getType() {
        return type;
    }
}
