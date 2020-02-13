package ru.otus.hw04.shell.dao.creators;

import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.QuestionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface QuestionCreator {

    Question createQuestion(String body, String correct, String wrong);

    QuestionType getType();

    default List<String> splitAnswers(String answers) {
        List<String> list = Arrays.asList(answers.split("\\$"));
        list.removeAll(new HashSet<String>() {{
            add("");
        }});
        return list;
    }
}
