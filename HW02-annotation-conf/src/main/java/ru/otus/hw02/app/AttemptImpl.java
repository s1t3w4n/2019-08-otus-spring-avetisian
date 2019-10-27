package ru.otus.hw02.app;


import ru.otus.hw02.console.Console;
import ru.otus.hw02.dao.QuestionsDao;
import ru.otus.hw02.data.Question;
import ru.otus.hw02.exceptions.AnswerException;

import java.io.IOException;
import java.util.List;

public class AttemptImpl implements Attempt {
    private final String name;
    private final String surname;
    private final List<Question> questions;
    private final MarkCalc markCalc;
    private final Console console;
    private final int offset;

    public AttemptImpl(Console console,
                       QuestionsDao dao,
                       MarkCalc markCalc,
                       int offset) throws IOException {
        this.console = console;
        this.offset = offset;
        name = setName();
        surname = setSurname();
        this.questions = dao.loadQuestions();
        this.markCalc = markCalc;
    }

    private String setName() {
        String text;
        do {
            console.print("print.name", (Object) null);
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    private String setSurname() {
        String text;
        do {
            console.print("print.surname", (Object) null);
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    @Override
    public void start() {
        console.print("print.greeting", name, surname);
        console.print("print.patq", questions.size());
        for (Question question : questions) {
            console.print(QuestionMessageFactory.getTask(question), (Object) null);
            console.print(question.getQuestionText() + "\n");
            String answer;
            boolean correctAnswer = false;
            do {
                OptionsShuffler optionsShuffler = new RandomOptionsShufflerImpl(question.getOptions());
                console.print(QuestionMessageFactory.getOptions(question, optionsShuffler));
                console.print("print.yai", (Object) null);
                answer = console.read();
                try {
                    correctAnswer = markCalc.checkAnswer(answer, question, optionsShuffler);
                } catch (AnswerException e) {
                    console.print(e.getMessage(), (Object) null);
                }

            } while (!correctAnswer);
        }
        console.print("print.rota", showResult());
        console.print(checkOffset(offset, showResult()), surname, name, questions.size());
    }

    @Override
    public int showResult() {
        return markCalc.getResult();
    }

    private String checkOffset(int range, int result) {
        return result > range ? "print.offset.true" : "print.offset.false";
    }
}
