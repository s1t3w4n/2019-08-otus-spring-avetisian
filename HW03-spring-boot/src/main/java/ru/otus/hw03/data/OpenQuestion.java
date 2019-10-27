package ru.otus.hw03.data;

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

    /*@Override
    public String getQuestionText() {
        return body;
    }

    @Override
    public Set<String> getOptions() {
        return options;
    }

    @Override
    public boolean checkAnswer(String answer) {
        if (options.contains(answer)) {
            return true;
        } else {
            for (String option : options) {
                if (option.length() - 1 > answer.length()) {
                    continue;
                }
                if (isAnyEnding(option)) {
                    String tempAnswer = answer.substring(0, option.length() - 1) + "*";
                    if (tempAnswer.equals(option)) {
                        return true;
                    } else if (isAnyChar(option) && matchOptionWithAnyChars(tempAnswer, option)) {
                        return true;
                    }
                }
                if (isAnyChar(option) && option.length() == answer.length() && matchOptionWithAnyChars(answer, option)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getQuantityOfRightOptions() {
        return RIGHT_OPTIONS_QUANTITY;
    }

    @Override
    public QuestionType getType() {
        return type;
    }*/

    private boolean isAnyEnding(String option) {
        return option.endsWith("*");
    }

    private boolean isAnyChar(String option) {
        return option.contains("*");
    }

    private boolean matchOptionWithAnyChars(String answer, String option) {
        char[] answerChars = answer.toCharArray();
        char[] optionChars = option.toCharArray();

        for (int i = 0; i < optionChars.length; i++) {
            if (optionChars[i] != '*') {
                if (optionChars[i] != answerChars[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
