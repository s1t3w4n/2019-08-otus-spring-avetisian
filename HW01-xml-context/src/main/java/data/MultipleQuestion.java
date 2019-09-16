package data;

import java.util.*;

public class MultipleQuestion implements Question {

    private final String body;
    private final Map<String, Integer> answers;
    private Map<Integer, String> sequence;

    public MultipleQuestion(String body, List<String> correctOptions, List<String> wrongOptions) {
        this.body = body;
        answers = fillAnswers(correctOptions, wrongOptions);
    }

    @Override
    public void printQuestion() {
        System.out.println("=============================");
        System.out.println("Please answer the question:");
        System.out.println(body);
        System.out.println("Choose one or more options: ");

        sequence = new HashMap<>();

        List<String> temp = new ArrayList<>(answers.keySet());

        for (int i = 1; i <= answers.size(); i++) {
            int index = (int) (Math.random() * temp.size());
            sequence.put(i, temp.get(index));
            temp.remove(index);
            System.out.println((i) + ": " + sequence.get(i));
        }

        System.out.println("Print numbers of correct option:");
        System.out.println("Example:5;1;4");
        System.out.print("Your answer:");
    }

    @Override
    public int getMark(String answer) {
        Set<String> answerOptions = new HashSet<>(Arrays.asList(answer.split(";")));
        int mark = 0;

        for (String option : answerOptions) {
            mark += answers.get(sequence.get(Integer.parseInt(option)));
        }

        if (mark < 0) {
            return 0;
        } else if (mark > 100) {
            return 100;
        } else {
            return mark;
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        Set<String> answerOptions = new HashSet<>(Arrays.asList(answer.split(";")));
        Set<Integer> numbers = new HashSet<>();
        for (String option : answerOptions) {
            try {
                int number = Integer.parseInt(option);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println("You have to print only a numbers of correct options");
                return false;
            }
        }
        System.out.println("Answer format is correct");
        if (sequence.keySet().containsAll(numbers)) {
            return true;
        } else {
            System.out.println("There is no such numbers in options");
            return false;
        }
    }

    private Map<String, Integer> fillAnswers(List<String> correctOptions, List<String> wrongOptions) {
        Map<String, Integer> map = new HashMap<>();

        int value = (int) Math.ceil(100f / correctOptions.size());

        for (String option : correctOptions) {
            map.put(option, value);
        }
        for (String option : wrongOptions) {
            map.put(option, -100);
        }
        return map;
    }
}
