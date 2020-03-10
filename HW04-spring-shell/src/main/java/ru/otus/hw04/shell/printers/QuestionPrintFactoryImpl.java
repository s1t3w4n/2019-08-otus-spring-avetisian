package ru.otus.hw04.shell.printers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.QuestionPrintFactory;
import ru.otus.hw04.shell.app.QuestionPrinter;
import ru.otus.hw04.shell.data.Question;
import ru.otus.hw04.shell.exceptions.CantPrintQuestionException;

import java.util.List;

@Service
public class QuestionPrintFactoryImpl implements QuestionPrintFactory {

    private final List<QuestionPrinter> printers;

    @Autowired
    public QuestionPrintFactoryImpl(List<QuestionPrinter> printers) {
        this.printers = printers;
    }

    @Override
    public String printQuestion(Question question) throws CantPrintQuestionException {
        for (QuestionPrinter printer : printers) {
            if (printer.getTYPE().equals(question.getType())) {
                return printer.printQuestion(question.getQuestionParts());
            }
        }
        throw new CantPrintQuestionException("exception.print");
    }
}
