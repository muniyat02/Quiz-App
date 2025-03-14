import java.util.List;

public class Subject {
    private final String name;
    private final List<String> questions;
    private final List<List<String>> options;
    private final List<String> correctAnswers;

    public Subject(String name, List<String> questions, List<List<String>> options, List<String> correctAnswers) {
        this.name = name;
        this.questions = questions;
        this.options = options;
        this.correctAnswers = correctAnswers;
    }

    public String getName() {
        return name;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<List<String>> getOptions() {
        return options;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }
}
