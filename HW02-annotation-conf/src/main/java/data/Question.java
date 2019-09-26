package data;

import java.util.Set;

public interface Question {

    String getQuestionText();
    Set<String> getOptions();
    boolean checkAnswer(String answer);
    int getQuantityOfRightOptions();
    QuestionType getType();
}
