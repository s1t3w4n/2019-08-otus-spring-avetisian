package dao;

import data.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {
    List<Question> loadQuestions() throws IOException;
}
