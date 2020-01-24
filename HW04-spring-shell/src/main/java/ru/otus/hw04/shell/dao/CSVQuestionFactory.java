package ru.otus.hw04.shell.dao;

import org.apache.commons.csv.CSVRecord;
import ru.otus.hw04.shell.data.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static ru.otus.hw04.shell.dao.CSVHeaders.*;

class CSVQuestionFactory {

    static Question getQuestion(CSVRecord record) {

        QuestionType questionType = QuestionType.valueOf(record.get(type).toUpperCase());

        switch (questionType) {
            case SIMPLE:
                return new SimpleQuestion(record.get(body),
                        record.get(correct),
                        splitAnswers(record.get(wrong)));
            case MULTIPLE:
                return new MultipleQuestion(record.get(body),
                        splitAnswers(record.get(correct)),
                        splitAnswers(record.get(wrong)));
            case OPEN:
                return new OpenQuestion(record.get(body),
                        splitAnswers(record.get(correct)));
            default:
                return null;
        }
    }

    private static List<String> splitAnswers(String answers) {
        List<String> list = Arrays.asList(answers.split("\\$"));
        list.removeAll(new HashSet<String>(){{add("");}});
        return list;
    }

}
