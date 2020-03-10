package ru.otus.hw04.shell.printers;

import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.app.PrintAdapter;
import ru.otus.hw04.shell.app.QuestionPrinter;
import ru.otus.hw04.shell.data.QuestionType;

@Component
public class SimpleQuestionPrinter implements QuestionPrinter {

    private final PrintAdapter printAdapter;

    private static final QuestionType TYPE = QuestionType.SIMPLE;

    public SimpleQuestionPrinter(PrintAdapter printAdapter) {
        this.printAdapter = printAdapter;
    }

    @Override
    public QuestionType getTYPE() {
        return TYPE;
    }

    @Override
    public String printQuestion(String... variables) {
        return printAdapter.print("question.simple.text", (Object[]) variables);
    }
}
