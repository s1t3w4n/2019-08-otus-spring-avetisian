package data;

import java.util.*;

public class SimpleQuestion implements Question {

    private final String body;
    private final Map<String, Integer> answers;
    private Map<Integer, String> sequence;

    public SimpleQuestion(String body, String correctOption, List<String> wrongOptions) {
        this.body = body;
        answers = fillAnswers(correctOption, wrongOptions);
    }

    @Override
    public void printQuestion() {
        System.out.println("Please answer the question:");
        System.out.println(body);
        System.out.println("Choose one option: ");
        sequence = new HashMap<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= answers.size(); i++) {
            numbers.add(i);
        }

        for (String s : answers.keySet()) {
            int i = (int)(Math.random()*(answers.size()-numbers.size()));
            sequence.put(numbers.get(i),s);
            numbers.remove(i);
        }
        System.out.print("Print number of option:");

    }

    @Override
    public int checkAnswer(String answer) {
        int mark = answers.get(sequence.get(Integer.parseInt(answer)));
        if (mark < 0) {
            return 0;
        } else {
            return mark;
        }
    }

    private Map<String, Integer> fillAnswers(String correctOption, List<String> wrongOptions) {
        Map<String, Integer> map = new HashMap<>();
        map.put(correctOption, 100);
        for (String option : wrongOptions) {
            map.put(option, -100);
        }
        return map;
    }


}
