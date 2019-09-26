package app;

import java.util.*;

public class RandomAnswerShufflerImpl implements AnswerShuffler {
    private final Map<Integer, String> answersMap;

    private RandomAnswerShufflerImpl(Set<String> answers) {
        answersMap = setNumbers(answers);
    }

    @Override
    public RandomAnswerShufflerImpl shuffleAnswers() {
        return new RandomAnswerShufflerImpl(new HashSet<>(answersMap.values()));
    }

    @Override
    public Map<Integer, String> getSequence() {
        return answersMap;
    }

    @Override
    public String getAnswer(int key) {
        return answersMap.get(key);
    }

    private Map<Integer, String> setNumbers(Set<String> answers) {
        Map<Integer, String> map = new HashMap<>();
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= answers.size(); i++) {
            numbers.add(i);
        }
        for (String answer : answers) {
            int key = random.nextInt(numbers.size());
            map.put(key, answer);
            numbers.remove(key);
        }
        return map;
    }
}
