package console;

import dao.QuestionsDao;
import data.Question;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    private final Scanner scanner;
    private final QuestionsDao questionsDao;

    public ConsoleServiceImpl(QuestionsDao questionsDao) {
        scanner = new Scanner(System.in);
        this.questionsDao = questionsDao;
    }

    @Override
    public String askName() {
        System.out.print("Print your name: ");
        return scanner.nextLine();
    }

    @Override
    public String askSurname() {
        System.out.print("Print your name surname: ");
        return scanner.nextLine();
    }

    @Override
    public int askQuestions() {
        int quantityOfQuestions = 0;
        int sumOfMarks = 0;
        try {
            List<Question> questions = questionsDao.loadQuestions();
            quantityOfQuestions = questions.size();
            for (Question question : questions) {
                String answer;
                do {
                    question.printQuestion();
                    answer = scanner.nextLine();
                } while (!question.checkAnswer(answer));
                sumOfMarks += question.getMark(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (quantityOfQuestions != 0) {
            return sumOfMarks / quantityOfQuestions;
        } else {
            return 0;
        }
    }
}
