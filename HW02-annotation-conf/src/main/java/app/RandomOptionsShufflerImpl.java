package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RandomOptionsShufflerImpl implements OptionsShuffler {
    private final Map<Integer, String> answersMap;

    @Autowired
    public RandomOptionsShufflerImpl() {
        answersMap = Collections.emptyMap();
    }

    private RandomOptionsShufflerImpl(Set<String> answers) {
        answersMap = setNumbers(answers);
    }

    @Override
    public RandomOptionsShufflerImpl shuffleAnswers(Set<String> answers) {
        return new RandomOptionsShufflerImpl(answers);
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
