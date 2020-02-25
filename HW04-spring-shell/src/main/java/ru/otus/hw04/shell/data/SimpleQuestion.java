package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.helpers.PercentHelper;

import java.util.*;

public class SimpleQuestion extends Question {

    private final Map<String, Boolean> options;
    private final Map<Integer, String> shuffler;

    public SimpleQuestion(String body,
                          String rightOption,
                          List<String> wrongOptions) {
        super(body);
        options = new HashMap<>();
        shuffler = new HashMap<>();

        options.put(rightOption, true);
        for (String wrongOption : wrongOptions) {
            if (!wrongOption.equals(rightOption)) {
                options.put(wrongOption, false);
            }
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public String[] getQuestionParts() {
        String zeroBundleVatiable = "\n" + body + "\n";
        shuffle();
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Integer, String> entry : shuffler.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        int exampleIndex = (int) (Math.random() * options.size()) + 1;
        return new String[]{"question.simple.text",
                zeroBundleVatiable,
                sb.toString(),
                "\n",
                Integer.toString(exampleIndex)
        };
    }

    @Override
    public int rateTheAnswer(String answer) {
        int mark = -1;
        try {
            mark = Integer.parseInt(answer);
            mark = options.get(shuffler.get(mark)) ? PercentHelper.getMaxPercent() : 0;
        } catch (NumberFormatException ignored) {
        }
        return mark;
    }

    private void shuffle() {
        List<String> temp = new ArrayList<>(options.keySet());
        Collections.shuffle(temp);

        for (int i = 1; i <= options.size(); i++) {
            shuffler.put(i, temp.get(i - 1));
        }
    }

}
