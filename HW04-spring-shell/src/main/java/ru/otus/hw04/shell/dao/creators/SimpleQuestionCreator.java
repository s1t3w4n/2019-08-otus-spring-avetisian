package ru.otus.hw04.shell.dao.creators;

import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.QuestionType;
import ru.otus.hw04.shell.data.SimpleQuestion;

@Component
public class SimpleQuestionCreator implements QuestionCreator {

    private static final QuestionType TYPE = QuestionType.SIMPLE;

    @Override
    public Question createQuestion(String body,
                                   String correct,
                                   String wrong) {
        return new SimpleQuestion(body,
                correct,
                splitAnswers(wrong));
    }

    @Override
    public QuestionType getType() {
        return TYPE;
    }
}
