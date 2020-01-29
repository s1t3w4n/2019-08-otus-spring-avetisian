package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.dao.QuestionPrintAdapter;

import java.util.*;

public class MultipleQuestion implements Question {

    private final String body;
    private final Map<String, Boolean> options;
    private final Map<Integer, String> shuffler;

    private static final QuestionType type = QuestionType.MULTIPLE;

    public MultipleQuestion(String body, List<String> correctOptions, List<String> wrongOptions) {
        this.body = body;
        options = new HashMap<>();
        shuffler = new HashMap<>();

        for (String correctOption : correctOptions) {
            options.put(correctOption, true);
        }

        for (String wrongOption : wrongOptions) {
            if (!options.keySet().contains(wrongOption)) {
                options.put(wrongOption, false);
            }
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public String printQuestion(QuestionPrintAdapter qpa) {
        String variable0 = "\n" + body + "\n";
        shuffle();
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Integer, String> entry : shuffler.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        LinkedList<Integer> numbers = new LinkedList<>(shuffler.keySet());

        String example = String.valueOf(numbers.getFirst()) +
                ";" +
                numbers.getLast();
        return qpa.print("question.multiple.text",
                variable0,
                sb.toString(),
                example);
    }

    @Override
    public int rateTheAnswer(String answer) {
        int mark = -1;
        int rightOptions = 0;
        Set<Integer> answers = new HashSet<>();

        try {
            for (String s : answer.split(";")) {
                answers.add(Integer.parseInt(s));
            }
            for (Integer number : answers) {
                if (!shuffler.containsKey(number)) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {
            return mark;
        }

        for (Map.Entry<String, Boolean> option : options.entrySet()) {
            if (option.getValue()) {
                rightOptions++;
            }
        }

        int rightAnsweredOptionsCount = 0;

        for (Integer integer : answers) {
            if (options.get(shuffler.get(integer))) {
                rightAnsweredOptionsCount++;
            } else {
                return 0;
            }
        }

        if (rightAnsweredOptionsCount == rightOptions) {
            return 100;
        } else {
            return ((int) Math.ceil(100f / rightOptions) * rightAnsweredOptionsCount);
        }
    }

    private void shuffle() {
        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= options.size(); i++) {
            temp.add(i);
        }
        for (String option : options.keySet()) {
            int index = (int) (Math.random() * temp.size());
            shuffler.put(temp.get(index), option);
            temp.remove(index);
        }
    }
}
