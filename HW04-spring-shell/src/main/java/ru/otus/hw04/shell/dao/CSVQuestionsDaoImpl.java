package ru.otus.hw04.shell.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.CSVQuestionFactory;
import ru.otus.hw04.shell.data.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@PropertySource("classpath:application.yml")
@Service
public class CSVQuestionsDaoImpl implements QuestionsDao {
    private Reader reader;
    private final CSVQuestionFactory factory;

    public CSVQuestionsDaoImpl(@Value("${db.url}") String filePath, CSVQuestionFactory factory) {
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.factory = factory;
    }

    @Override
    public List<Question> loadQuestions() throws IOException {

        CSVParser records = CSVFormat.DEFAULT
                .withHeader(CSVHeaders.class)
                .withFirstRecordAsHeader()
                .parse(reader);

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
