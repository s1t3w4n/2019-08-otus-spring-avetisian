package ru.otus.hw04.shell.data;

import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.helpers.PercentHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenQuestion extends Question {

    private final Set<String> options;
    private final Pattern symbol;
    private final Pattern ending;

    public OpenQuestion(QuestionPrintAdapter questionPrintAdapter,
                        String body,
                        List<String> correctOptions) {
        super(questionPrintAdapter, body);
        options = new HashSet<>(correctOptions);
        symbol = Pattern.compile("\\*");
        ending = Pattern.compile("\\*$");
    }

    @Override
    public String printQuestion() {
        String variable0 = "\n" + body + "\n";
        return questionPrintAdapter.print("question.open.text", variable0, "\n");
    }

    @Override
    public int rateTheAnswer(String answer) {
        int mark = -1;
        if (answer.isEmpty()) {
            return mark;
        }

        for (String option : options) {
            option = "^" + option;
            Matcher endMatcher = ending.matcher(option);
            if (endMatcher.find()) {
                option = endMatcher.replaceAll("");
            }
            Matcher symbolMatcher = symbol.matcher(option);
            if (symbolMatcher.find()) {
                option = symbolMatcher.replaceAll(".");
            }
            Pattern pattern = Pattern.compile(option);
            Matcher matcher = pattern.matcher(answer);

            if (matcher.find(0)) {
                return PercentHelper.getMaxPercent();
            }
        }

        return 0;
    }
}
