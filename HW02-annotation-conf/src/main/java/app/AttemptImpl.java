package app;


import console.Console;
import data.Question;
import exceptions.AnswerException;

import java.util.List;

public class AttemptImpl implements Attempt {
    private final String name;
    private final String surname;
    private final List<Question> questions;
    private final MarkCalc markCalc;
    private final Console console;

    public AttemptImpl(Console console,
                       List<Question> questions,
                       MarkCalc markCalc) {
        this.console = console;
        name = setName();
        surname = setSurname();
        this.questions = questions;
        this.markCalc = markCalc;
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
