package ru.otus.hw02.dao;

import ru.otus.hw02.data.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {
    List<Question> loadQuestions() throws IOException;
}
