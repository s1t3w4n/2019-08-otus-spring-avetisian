package ru.otus.hw03.dao;

import ru.otus.hw03.data.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {
    List<Question> loadQuestions() throws IOException;
}
