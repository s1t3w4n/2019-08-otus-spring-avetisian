package data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenQuestion implements Question {

    private final String body;
    private final Set<String> options;

    private static final int RIGHT_OPTIONS_QUANTITY = 1;

    public OpenQuestion(String body, List<String> correctOptions) {
        this.body = body;
        options = new HashSet<>(correctOptions);
    }

    @Override
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
                if (option.length() > answer.length()) {
                    continue;
                }
                if (isAnyEnding(option)) {
                    String tempAnswer = answer.substring(0, option.length() - 1) + "*";
                    if (isAnyChar(option)) {
                        boolean matched = matchOptionWithAnyChars(tempAnswer, option);
                        if (matched) {
                            return true;
                        }
                    } else {
                        if (tempAnswer.equals(option)) {
                            return true;
                        }
                    }
                }
                if (isAnyChar(option) && option.length() == answer.length()) {
                    boolean matched = matchOptionWithAnyChars(answer, option);
                    if (matched) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getQuantityOfRightOptions() {
        return RIGHT_OPTIONS_QUANTITY;
    }

    private boolean isAnyEnding(String option) {
        return option.endsWith("*");
    }

    private boolean isAnyChar(String option) {
        List chars = Arrays.asList(option.toCharArray());
        return chars.contains('*');
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
