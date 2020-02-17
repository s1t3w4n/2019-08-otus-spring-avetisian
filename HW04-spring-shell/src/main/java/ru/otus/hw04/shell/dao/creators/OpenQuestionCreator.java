package ru.otus.hw04.shell.dao.creators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.data.OpenQuestion;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.QuestionType;

@Component
public class OpenQuestionCreator implements QuestionCreator {

    private static final QuestionType TYPE = QuestionType.OPEN;

    private final QuestionPrintAdapter questionPrintAdapter;

    @Autowired
    public OpenQuestionCreator(QuestionPrintAdapter questionPrintAdapter) {
        this.questionPrintAdapter = questionPrintAdapter;
    }


    @Override
    public Question createQuestion(String body,
                                   String correct,
                                   String wrong) {
        return new OpenQuestion(questionPrintAdapter,
                body,
                splitAnswers(correct));
    }

    @Override
    public QuestionType getType() {
        return TYPE;
    }
}
