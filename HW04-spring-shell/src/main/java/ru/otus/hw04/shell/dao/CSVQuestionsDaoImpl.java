package ru.otus.hw04.shell.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.CSVQuestionFactory;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.exceptions.QuestionLoadingFailedException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CSVQuestionsDaoImpl implements QuestionsDao {
    private Reader reader;
    private final CSVQuestionFactory factory;

    public CSVQuestionsDaoImpl(@Value("${db.url}") String filePath, CSVQuestionFactory factory) {
        ClassLoader classLoader = getClass().getClassLoader();
        reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(filePath)));
        this.factory = factory;
    }

    @Override
    public List<Question> loadQuestions() throws QuestionLoadingFailedException {

        CSVParser records;
        try {
            records = CSVFormat.DEFAULT
                    .withHeader(CSVHeaders.class)
                    .withFirstRecordAsHeader()
                    .parse(reader);
        } catch (IOException e) {
            throw new QuestionLoadingFailedException("exception.load");
        }

        List<Question> questions = new ArrayList<>();

        for (CSVRecord record : records) {
            Question question = factory.getQuestion(record);
            if (Objects.nonNull(question)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
