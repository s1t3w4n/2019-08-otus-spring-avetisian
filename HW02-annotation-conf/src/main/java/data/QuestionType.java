package data;

public enum QuestionType {
    SIMPLE ("simple"),
    MULTIPLY ("multiply"),
    OPEN ("open");

    private final String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
