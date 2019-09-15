package dao;

import data.Question;
import data.SimpleQuestion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    private final Reader reader;

    private final static String[] HEADERS = {"body", "type", "correct", "wrong"};

    public QuestionsDaoImpl(String filePath) throws FileNotFoundException {
        reader = new FileReader(filePath);
    }

    @Override
    public List<Question> loadQuestions() throws IOException {
        CSVParser records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(reader);
        List<Question> questions = new ArrayList<>();
        if (records.getHeaderNames().containsAll(Arrays.asList(HEADERS))) {
            for (CSVRecord record : records) {
                switch (record.get(HEADERS[1])) {
                    case "simple":
                        questions.add(new SimpleQuestion(record.get(HEADERS[0]),
                                record.get(HEADERS[2]),
                                Arrays.asList(record.get(HEADERS[3]).split("\\$"))));
                        break;
                }
            }
            return questions;
        } else {
            throw new IOException("Wrong file structure...");
        }
    }
}
