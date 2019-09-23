package dao;

import data.Question;
import data.QuestionType;
import data.SimpleQuestion;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;

import static dao.CSVHeaders.*;

class CSVQuestionFactory {

    public static Question getQuestion(CSVRecord record) {

        QuestionType questionType = QuestionType.valueOf(record.get(type));

        switch (questionType) {
            case SIMPLE:
                return new SimpleQuestion(record.get(body),
                        record.get(correct),
                        splitAnswers(record.get(wrong)));
            case MULTIPLY:
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
        return Arrays.asList(answers.split("\\$"));
    }

}
