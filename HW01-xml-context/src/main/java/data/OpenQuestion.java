package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OpenQuestion implements Question {

    private final String body;
    private final Map<String, Integer> answers;

    public OpenQuestion(String body, List<String> correctOptions) {
        this.body = body;
        answers = fillAnswers(correctOptions);
    }

    @Override
    public void printQuestion() {
        System.out.println("=============================");
        System.out.println("Please answer the question:");
        System.out.println(body);
        System.out.print("Print the answer: ");
    }

    @Override
    public int getMark(String answer) {
        while (answer.startsWith(" ")) {
            answer = answer.replaceFirst(" ", "");
        }
        answer = answer.toLowerCase();
        if (answers.keySet().contains(answer)) {
            return 100;
        } else if (anyEnding()) {
            for (String s : answers.keySet()) {
                if (s.endsWith("*")) {
                    int length = s.toCharArray().length - 1;
                    if (answer.toCharArray().length >= length) {
                        answer = answer.substring(0, length) + "*";
                        if (answers.keySet().contains(answer)) {
                            return 100;
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public boolean checkAnswer(String answer) {
        if(Objects.nonNull(answer)){
            if (answer.isEmpty()) {
                System.out.println("You must print something");
                return false;
            }
        }
        return true;
    }

    private Map<String, Integer> fillAnswers(List<String> correctOptions) {
        Map<String, Integer> map = new HashMap<>();

        for (String option : correctOptions) {
            map.put(option, 100);
        }

        return map;
    }

    private boolean anyEnding() {
        for (String s : answers.keySet()) {
            if (s.endsWith("*")) {
                return true;
            }
        }
        return false;
    }
}
