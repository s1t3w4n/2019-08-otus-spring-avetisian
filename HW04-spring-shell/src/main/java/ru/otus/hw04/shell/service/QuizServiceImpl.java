package ru.otus.hw04.shell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.IntroduceService;
import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.app.QuizService;
import ru.otus.hw04.shell.dao.QuestionsDao;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.exceptions.QuestionLoadingFailedException;

import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionPrintAdapter questionPrintAdapter;
    private final IntroduceService introduceService;
    private final ArrayDeque<Question> questions;
    private final Map<Question, Integer> marks;
    private final int offset;


    public QuizServiceImpl(QuestionPrintAdapter questionPrintAdapter,
                           QuestionsDao questionsDao,
                           IntroduceService introduceService,
                           @Value("${offset}") int offset) throws QuestionLoadingFailedException {
        this.offset = offset;
        this.questionPrintAdapter = questionPrintAdapter;
        this.introduceService = introduceService;
        questions = new ArrayDeque<>(questionsDao.loadQuestions());
        marks = new HashMap<>();
    }

    @Override
    public String continueQuiz(String data) {
        if (!introduceService.isIntroduced()) {
            String message = addLn(questionPrintAdapter.print(introduceService.introduce(data)));
            if (!introduceService.isIntroduced()) {
                return message;
            }
        }
        if (questions.isEmpty()) {
            return getResult();
        }
        if (Objects.nonNull(data)) {
            int mark = questions.getFirst().rateTheAnswer(data);
            if (mark < 0) {
                return addLn(questionPrintAdapter.print("answer.incorrect")) +
                        getCurrentQuestion();
            } else {
                marks.put(questions.getFirst(), mark);
                questions.removeFirst();
                if (questions.isEmpty()) {
                    return getResult();
                }
                return addLn(questionPrintAdapter.print("answer.correct")) +
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

        return addLn(questionPrintAdapter.print("print.result", result))
                + questionPrintAdapter.print(
                result > offset ? "print.offset.true" : "print.offset.false",
                introduceService.askName(),
                introduceService.askSurname(),
                marks.size());
    }

    private String getCurrentQuestion() {
        return questionPrintAdapter.print(questions.getFirst().getQuestionParts());
    }

    private String addLn(String string) {
        return string + "\n";
    }

}
