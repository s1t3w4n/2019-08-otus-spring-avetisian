package ru.otus.hw04.shell.dao;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.CSVQuestionFactory;
import ru.otus.hw04.shell.dao.creators.QuestionCreator;
import ru.otus.hw04.shell.data.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.hw04.shell.dao.CSVHeaders.*;

@Service
class CSVQuestionFactoryImpl implements CSVQuestionFactory {

    private final Map<QuestionType, QuestionCreator> creators;

    @Autowired
    public CSVQuestionFactoryImpl(List<QuestionCreator> creators) {
        this.creators = fillCreators(creators);
    }

    @Override
    public Question getQuestion(CSVRecord record) {

        QuestionType questionType = QuestionType.valueOf(record.get(type).toUpperCase());
        if (creators.containsKey(questionType)) {
            return creators.get(questionType).createQuestion(record.get(body),
                    record.get(correct),
                    record.get(wrong));
        } else return null;
    }

    private Map<QuestionType, QuestionCreator> fillCreators(List<QuestionCreator> creators) {
        HashMap<QuestionType, QuestionCreator> questionTypeQuestionCreatorHashMap = new HashMap<>();
        for (QuestionCreator creator : creators) {
            questionTypeQuestionCreatorHashMap.put(creator.getType(), creator);
        }
        return questionTypeQuestionCreatorHashMap;
    }
}
