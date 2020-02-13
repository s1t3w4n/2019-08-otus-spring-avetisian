package ru.otus.hw04.shell.dao.creators;

import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.data.MultipleQuestion;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.QuestionType;

@Component
public class MultipleQuestionCreator implements QuestionCreator {

    private static final QuestionType TYPE = QuestionType.MULTIPLE;

    @Override
    public Question createQuestion(String body, String correct, String wrong) {
        return new MultipleQuestion(body, splitAnswers(correct), splitAnswers(wrong));
    }

    @Override
    public QuestionType getType() {
        return TYPE;
    }
}
