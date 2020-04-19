package ru.otus.hw04.shell.dao;

import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.exceptions.QuestionLoadingFailedException;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {
    List<Question> loadQuestions() throws QuestionLoadingFailedException;
}
