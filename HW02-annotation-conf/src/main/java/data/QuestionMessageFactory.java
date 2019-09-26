package data;


public class QuestionMessageFactory {

    public static String printAnswer(Question question) {
        switch (question.getType()) {
            case SIMPLE:
                return "Print number of correct answer";
            case MULTIPLE:
                return "Print number of correct answers";
            case OPEN:
                return "Print answer";
            default:
                return "Print something";
        }
    }
}
