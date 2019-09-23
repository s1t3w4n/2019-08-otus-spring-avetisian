package dao;

import data.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CSVQuestionsDaoImpl implements QuestionsDao {
    private final Reader reader;

    public CSVQuestionsDaoImpl(String filePath) throws FileNotFoundException {
        reader = new FileReader(filePath);
    }

    @Override
    public List<Question> loadQuestions() throws IOException {

        CSVParser records = CSVFormat.DEFAULT
                .withHeader(CSVHeaders.class)
                .withFirstRecordAsHeader()
                .parse(reader);

        List<Question> questions = new ArrayList<>();

        if (records.getHeaderNames().containsAll(Arrays.asList(CSVHeaders.values()))) {
            for (CSVRecord record : records) {
                Question question = CSVQuestionFactory.getQuestion(record);
                if (Objects.nonNull(question)) {
                    questions.add(question);
                }
            }
            return questions;
        } else {
            throw new IOException("Wrong file structure...");
        }
    }
}
