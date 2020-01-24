package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.dao.QuestionPrintAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleQuestion implements Question {

    private final String body;
    private final Map<String, Boolean> options;
    private final Map<Integer, String> shuffler;

    private static final QuestionType type = QuestionType.SIMPLE;

    public SimpleQuestion(String body, String rightOption, List<String> wrongOptions) {
        this.body = body;
        options = new HashMap<>();
        shuffler = new HashMap<>();

        options.put(rightOption, true);
        for (String wrongOption : wrongOptions) {
            if (!wrongOption.equals(rightOption)) {
                options.put(wrongOption, false);
            }
        }
    }

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
        return qpa.print("question.simple.text",
                variable0,
                sb.toString(),
                (Math.random()*options.size()) + 1);
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
