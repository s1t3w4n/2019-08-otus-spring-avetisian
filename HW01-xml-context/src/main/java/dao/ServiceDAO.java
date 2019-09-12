package dao;

import data.Question;
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

public class ServiceDAO {
    private final String FILE;
    private final Reader reader;

    private final static String[] HEADERS = {"body", "type", "correct", "wrong"};

    public ServiceDAO(String file) throws FileNotFoundException {
        FILE = file;
        reader = new FileReader(FILE);
    }

    public List<Question> loadQuestions() throws IOException {
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(reader);
        if (((CSVParser) records).getHeaderNames().containsAll(Arrays.asList(HEADERS))) {
            for (CSVRecord record : records) {
                switch (record.get("type")) {
                    case "simple":
                }
            }
            return new ArrayList<Question>();
        } else {
            throw new IOException("Wrong file structure...");
        }
    }
}
