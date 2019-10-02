package ru.otus.hw02.app;


import org.springframework.context.MessageSource;
import ru.otus.hw02.console.Console;
import ru.otus.hw02.dao.QuestionsDao;
import ru.otus.hw02.data.Question;
import ru.otus.hw02.exceptions.AnswerException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AttemptImpl implements Attempt {
    private final String name;
    private final String surname;
    private final List<Question> questions;
    private final MarkCalc markCalc;
    private final Console console;
    private final MessageSource messageSource;

    public AttemptImpl(Console console,
                       QuestionsDao dao,
                       MarkCalc markCalc,
                       MessageSource ms) throws IOException {
        this.console = console;
        this.messageSource = ms;
        name = setName();
        surname = setSurname();
        this.questions = dao.loadQuestions();
        this.markCalc = markCalc;
    }

    private String setName() {
        String text;
        do {
            console.print(messageSource.getMessage("print.name", null, Locale.getDefault()));
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    private String setSurname() {
        String text;
        do {
            console.print(messageSource.getMessage("print.surname", null, Locale.ENGLISH));
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    @Override
    public void start() {
        console.print("Dear " + surname + " " + name + " \n");
        console.print("Please answer to " + questions.size() + " questions: \n");
        for (Question question : questions) {
            console.print(QuestionMessageFactory.getTask(question) + "\n");
            console.print(question.getQuestionText() + "\n");
            String answer;
            boolean correct = false;
            do {
                OptionsShuffler optionsShuffler = new RandomOptionsShufflerImpl(question.getOptions());
                console.print(QuestionMessageFactory.getOptions(question, optionsShuffler));
                console.print("Your answer is: ");
                answer = console.read();
                try {
                    correct = QuestionMessageFactory.checkAnswer(answer, question, optionsShuffler);
                    markCalc.addMark(question.getQuantityOfRightOptions(), true);
                } catch (AnswerException e) {
                    console.print(e.getMessage());
                }

            } while (!correct);
        }
        console.print("Result of the attempt is: " + showResult());
    }

    @Override
    public int showResult() {
        return markCalc.getResult();
    }
}
