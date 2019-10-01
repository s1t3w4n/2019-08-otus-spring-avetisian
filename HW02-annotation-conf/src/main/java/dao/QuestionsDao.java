package dao;

import data.Question;

import java.util.List;

public interface QuestionsDao {
    List<Question> loadQuestions();
}
