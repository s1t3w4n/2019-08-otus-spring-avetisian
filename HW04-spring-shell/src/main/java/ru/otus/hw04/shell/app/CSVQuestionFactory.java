package ru.otus.hw04.shell.app;

import org.apache.commons.csv.CSVRecord;
import ru.otus.hw04.shell.data.Question;

public interface CSVQuestionFactory {
    Question getQuestion(CSVRecord record);
}
