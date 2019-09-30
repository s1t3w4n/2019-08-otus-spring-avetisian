package dao;

import data.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class CSVQuestionsDaoImpl implements QuestionsDao {
    private final Reader reader;

    @Autowired
    public CSVQuestionsDaoImpl(@Value("${db.url}") String filePath) throws FileNotFoundException {
        reader = new FileReader(filePath);
    }

    @Override
    public List<Question> loadQuestions() {

        CSVParser records = null;
        try {
            records = CSVFormat.DEFAULT
                    .withHeader(CSVHeaders.class)
                    .withFirstRecordAsHeader()
                    .parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Question> questions = new ArrayList<>();

        if (Objects.requireNonNull(records).getHeaderNames().containsAll(Arrays.asList(CSVHeaders.values()))) {
            for (CSVRecord record : records) {
                Question question = CSVQuestionFactory.getQuestion(record);
                if (Objects.nonNull(question)) {
                    questions.add(question);
                }
            }
            return questions;
        } else {
            return null;
        }
    }
}
