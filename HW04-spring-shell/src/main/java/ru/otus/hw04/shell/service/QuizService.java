package ru.otus.hw04.shell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.dao.QuestionPrintAdapter;
import ru.otus.hw04.shell.dao.QuestionsDao;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.data.SimpleQuestion;

import java.io.IOException;
import java.util.List;

@Service
public class QuizService {

    private final QuestionPrintAdapter qpa;
    private final QuestionsDao qd;
    private boolean isStarted = false;

    public QuizService(QuestionPrintAdapter qpa, QuestionsDao qd) {
        this.qpa = qpa;
        this.qd = qd;
    }

    public String start() throws IOException {
        List<Question> questions = qd.loadQuestions();
        for (Question question : questions) {
            return  ((SimpleQuestion)question).printQuestion(qpa);
        }
        return "sayonara";
    }
}
