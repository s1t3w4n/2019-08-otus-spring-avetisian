package ru.otus.hw04.shell.dao.creators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.QuestionType;
import ru.otus.hw04.shell.data.SimpleQuestion;

@Component
public class SimpleQuestionCreator implements QuestionCreator {

    private static final QuestionType TYPE = QuestionType.SIMPLE;

    private final QuestionPrintAdapter questionPrintAdapter;

    @Autowired
    public SimpleQuestionCreator(QuestionPrintAdapter questionPrintAdapter) {
        this.questionPrintAdapter = questionPrintAdapter;
    }

    @Override
    public Question createQuestion(String body,
                                   String correct,
                                   String wrong) {
        return new SimpleQuestion(questionPrintAdapter,
                body,
                correct,
                splitAnswers(wrong));
    }

    @Override
    public QuestionType getType() {
        return TYPE;
    }
}
