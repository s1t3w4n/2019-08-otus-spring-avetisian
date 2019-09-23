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
        System.out.println("=============================");
        System.out.println("Please answer the question:");
        System.out.println(body);
        System.out.println("Choose one option: ");

        sequence = new HashMap<>();

        List<String> temp = new ArrayList<>(answers.keySet());

        for (int i = 1; i <= answers.size(); i++) {
            int index = (int) (Math.random() * temp.size());
            sequence.put(i, temp.get(index));
            temp.remove(index);
            System.out.println((i) + ": " + sequence.get(i));
        }
        System.out.print("Print number of correct option:");
    }

    @Override
    public int getMark(String answer) {
        int mark = answers.get(sequence.get(Integer.parseInt(answer)));
        if (mark < 0) {
            return 0;
        } else {
            return mark;
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        try {
            int number = Integer.parseInt(answer);
            System.out.println("Answer format is correct");
            if (sequence.keySet().contains(number)) {
                return true;
            } else {
                System.out.println("There is no such number in options");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("You have to print only a number of correct option");
        }
        return false;
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
