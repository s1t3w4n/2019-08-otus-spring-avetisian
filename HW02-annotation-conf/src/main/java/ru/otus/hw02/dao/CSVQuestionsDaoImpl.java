package ru.otus.hw02.dao;

import ru.otus.hw02.data.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@PropertySource("classpath:application.properties")
@Service
public class CSVQuestionsDaoImpl implements QuestionsDao {
    private Reader reader;

    public CSVQuestionsDaoImpl(@Value("${db.url}") String filePath) {
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> loadQuestions() throws IOException {

        CSVParser records = CSVFormat.DEFAULT
                .withHeader(CSVHeaders.class)
                .withFirstRecordAsHeader()
                .parse(reader);

        List<Question> questions = new ArrayList<>();

        for (CSVRecord record : records) {
            Question question = CSVQuestionFactory.getQuestion(record);
            if (Objects.nonNull(question)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
