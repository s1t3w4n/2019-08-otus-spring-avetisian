package ru.otus.hw04.shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.dao.QuestionPrintAdapter;
import ru.otus.hw04.shell.dao.QuestionsDao;
import ru.otus.hw04.shell.data.Question;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class QuizService {

    private final QuestionPrintAdapter qpa;
    private final ArrayDeque<Question> questions;
    private final Map<Question, Integer> marks;
    private final int offset;

    private String name;
    private String surname;

    public QuizService(QuestionPrintAdapter qpa, QuestionsDao qd, @Value("${offset}") int offset) throws IOException {
        this.offset = offset;
        this.qpa = qpa;
        questions = new ArrayDeque<>(qd.loadQuestions());
        marks = new HashMap<>();
    }

    public String continueQuiz(String data) {
        if (Objects.isNull(name)) {
            if (Objects.isNull(data)) {
                return addLn(qpa.print("print.name"));
            }
            name = data;
            data = null;
        }
        if (Objects.isNull(surname)) {
            if (Objects.isNull(data)) {
                return addLn(qpa.print("print.surname"));
            }
            surname = data;
            data = null;
        }
        if (questions.isEmpty()) {
            return getResult();
        }
        if (Objects.nonNull(data)) {
            int mark = questions.getFirst().rateTheAnswer(data);
            if (mark < 0) {
                return addLn(qpa.print("answer.incorrect")) +
                        getCurrentQuestion();
            } else {
                marks.put(questions.getFirst(), mark);
                questions.removeFirst();
                if (questions.isEmpty()) {
                    return getResult();
                }
                return addLn(qpa.print("answer.correct")) +
                        getCurrentQuestion();
            }
        }

        return getCurrentQuestion();
    }

    private String getResult() {
        int result = 0;
        for (Integer value : marks.values()) {
            result += value;
        }
        result = result / marks.size();

        return addLn(qpa.print("print.result", result))
                + qpa.print(
                result > offset ? "print.offset.true" : "print.offset.false",
                name, surname, marks.size());
    }

    private String getCurrentQuestion() {
        return questions.getFirst().printQuestion(qpa);
    }

    private String addLn(String string) {
        return string + "\n";
    }
}
