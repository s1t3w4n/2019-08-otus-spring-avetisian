package ru.otus.hw03.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleQuestion implements Question {

    private final String body;
    private final Map<String, Boolean> options;

    private static final int RIGHT_OPTIONS_QUANTITY = 1;
    private static final QuestionType type = QuestionType.SIMPLE;

    public SimpleQuestion(String body, String rightOption, List<String> wrongOptions) {
        this.body = body;
        options = new HashMap<>();
        options.put(rightOption, true);
        for (String wrongOption : wrongOptions) {
            if (!wrongOption.equals(rightOption)) {
                options.put(wrongOption, false);
            }
        }
    }

   /* @Override
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
        return RIGHT_OPTIONS_QUANTITY;
    }

    @Override
    public QuestionType getType() {
        return type;
    }
*/
}
