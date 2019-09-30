package app;


import console.Console;
import dao.QuestionsDao;
import data.Question;
import exceptions.AnswerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptImpl implements Attempt {
    private String name;
    private String surname;
    private final List<Question> questions;
    private final MarkCalc markCalc;
    private final Console console;
    private final  OptionsShuffler optionsShuffler;

    public AttemptImpl(Console console,
                       QuestionsDao questionsDao,
                       MarkCalc markCalc,
                       OptionsShuffler optionsShuffler) {
        this.console = console;
//        name = setName();
//        surname = setSurname();
        questions = questionsDao.loadQuestions();
        this.markCalc = markCalc;
        this.optionsShuffler = optionsShuffler;
    }

    private String setName() {
        String text;
        do {
            console.print("Print your name please: ");
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    private String setSurname() {
        String text;
        do {
            console.print("Print your surname: ");
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    @Override
    public void start() {
        name = setName();
        surname = setSurname();
        console.print("Dear " + surname + " " + name);
        console.print("Please answer the questions");
        for (Question question : questions) {
            console.print(QuestionMessageFactory.getTask(question));
            console.print(question.getQuestionText());
            String answer;
            boolean correct = false;
            do {
                console.print(QuestionMessageFactory.getOptions(question, optionsShuffler));
                answer = console.read();
                try {
                    correct = QuestionMessageFactory.checkAnswer(answer, question, optionsShuffler);
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
