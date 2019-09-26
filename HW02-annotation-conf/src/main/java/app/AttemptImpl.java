package app;


import console.Console;
import dao.QuestionsDao;
import data.Question;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AttemptImpl implements Attempt {
    private final String name;
    private final String surname;
    private final List<Question> questions;
    private final int questionNumer;
    private final Mark mark;

    public AttemptImpl(Console console, List<Question> questions, Mark mark) {
        name = setName(console);
        surname = setSurname(console);
        this.questions = questions;
        questionNumer = questions.size();
        this.mark = mark;
    }

    private String setName(Console console) {
        String text;
        do {
            console.print("Print your name please: ");
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    private String setSurname(Console console) {
        String text;
        do {
            console.print("Print your surname: ");
            text = console.read();
        } while (text.isEmpty());
        return text;
    }

    private void start() {

    }
}
