package ru.otus.hw04.shell.printers;

import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.app.PrintAdapter;
import ru.otus.hw04.shell.app.QuestionPrinter;
import ru.otus.hw04.shell.data.QuestionType;

@Component
public class MultipleQuestionPrinter implements QuestionPrinter {

    private static final QuestionType TYPE = QuestionType.MULTIPLE;

    private final PrintAdapter printAdapter;

    public MultipleQuestionPrinter(PrintAdapter printAdapter) {
        this.printAdapter = printAdapter;
    }

    @Override
    public QuestionType getTYPE() {
        return TYPE;
    }

    @Override
    public String printQuestion(String... variables) {
        return printAdapter.print("question.multiple.text", (Object[]) variables);
    }
}
