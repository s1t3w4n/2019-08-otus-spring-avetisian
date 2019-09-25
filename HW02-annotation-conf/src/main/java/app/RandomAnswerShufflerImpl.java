package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomAnswerShufflerImpl implements AnswerShuffler {
    private final Map<Integer, String> answersMap;

    private RandomAnswerShufflerImpl(Set<String> answers) {
        answersMap = setNumbers(answers);
    }

    @Override
    public RandomAnswerShufflerImpl shuffleAnswers() {
        return null;
    }

    @Override
    public Map<Integer, String> getSequence() {
        return null;
    }

    @Override
    public String getAnswer(int i) {
        return null;
    }

    private Map<Integer,String> setNumbers(Set<String> answers) {
        Map<Integer, String> map = new HashMap<>();
        Random random = new Random();
        return map;
    }
}
