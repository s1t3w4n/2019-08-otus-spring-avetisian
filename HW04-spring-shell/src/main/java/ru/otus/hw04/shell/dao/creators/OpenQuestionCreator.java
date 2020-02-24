package ru.otus.hw04.shell.dao.creators;

import org.springframework.stereotype.Component;
import ru.otus.hw04.shell.data.OpenQuestion;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.QuestionType;

@Component
public class OpenQuestionCreator implements QuestionCreator {

    private static final QuestionType TYPE = QuestionType.OPEN;

    @Override
    public Question createQuestion(String body,
                                   String correct,
                                   String wrong) {
        return new OpenQuestion(body,
                splitAnswers(correct));
    }

    @Override
    public QuestionType getType() {
        return TYPE;
    }
}
