package ru.otus.hw04.shell.dao;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.CSVQuestionFactory;
import ru.otus.hw04.shell.dao.creators.QuestionCreator;
import ru.otus.hw04.shell.data.*;

import java.util.List;

import static ru.otus.hw04.shell.dao.CSVHeaders.*;

@Service
class CSVQuestionFactoryImpl implements CSVQuestionFactory {

    private final List<QuestionCreator> creators;

    @Autowired
    public CSVQuestionFactoryImpl(List<QuestionCreator> creators) {
        this.creators = creators;
    }

    @Override
    public Question getQuestion(CSVRecord record) {

        QuestionType questionType = QuestionType.valueOf(record.get(type).toUpperCase());
        for (QuestionCreator creator : creators) {
            if (creator.getType().equals(questionType)) {
                return creator.createQuestion(record.get(body),
                        record.get(correct),
                        record.get(wrong));
            }
        }
        return null;
    }

}
