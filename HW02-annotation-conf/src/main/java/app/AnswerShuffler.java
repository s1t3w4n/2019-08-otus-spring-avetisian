package app;

import java.util.Map;

public interface AnswerShuffler {
    RandomAnswerShufflerImpl shuffleAnswers();

    Map<Integer, String> getSequence();

    String getAnswer(int key);
}
