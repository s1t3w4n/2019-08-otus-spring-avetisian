package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.helpers.PercentHelper;

import java.util.*;

public class MultipleQuestion extends Question {

    private final Map<String, Boolean> options;
    private final Map<Integer, String> shuffler;

    public MultipleQuestion(QuestionPrintAdapter questionPrintAdapter,
                            String body,
                            List<String> correctOptions,
                            List<String> wrongOptions) {
        super(questionPrintAdapter, body);
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
    public String printQuestion() {
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
        return questionPrintAdapter.print("question.multiple.text",
                variable0,
                sb.toString(),
                "\n",
                example);
    }

    @Override
    public int rateTheAnswer(String answer) {
        int mark = -1;
        Set<Integer> answers;

        try {
            answers = splitAnswer(answer);
        } catch (NumberFormatException e) {
            return mark;
        }

        int rightOptions = sumRightOptions();

        int rightAnsweredOptionsCount = countRightAnsweredOptions(answers);

        return PercentHelper.calculateMultipleQuestionResult(rightAnsweredOptionsCount,rightOptions);
    }

    private void shuffle() {
        List<String> temp = new ArrayList<>(options.keySet());
        Collections.shuffle(temp);

        for (int i = 1; i <= temp.size(); i++) {
            shuffler.put(i, temp.get(i - 1));
        }
    }

    private Set<Integer> splitAnswer(String answer) throws NumberFormatException {
        Set<Integer> answers = new HashSet<>();
        for (String s : answer.split(";")) {
            answers.add(Integer.parseInt(s));
        }
        for (Integer number : answers) {
            if (!shuffler.containsKey(number)) {
                throw new NumberFormatException();
            }
        }
        return answers;
    }

    private int sumRightOptions() {
        int rightOptions = 0;
        for (Map.Entry<String, Boolean> option : options.entrySet()) {
            if (option.getValue()) {
                rightOptions++;
            }
        }
        return rightOptions;
    }

    private int countRightAnsweredOptions(Set<Integer> answers) {
        int rightAnsweredOptionsCount = 0;
        for (Integer integer : answers) {
            if (options.get(shuffler.get(integer))) {
                rightAnsweredOptionsCount++;
            }
        }
        return rightAnsweredOptionsCount;
    }
}
