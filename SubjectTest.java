import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {
    private Subject subject;

    @BeforeEach
    void setUp() {
        List<String> questions = Arrays.asList("What is Java?", "Who invented Java?");
        List<List<String>> options = Arrays.asList(
                Arrays.asList("A language", "A framework", "A database", "An OS"),
                Arrays.asList("James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum")
        );
        List<String> correctAnswers = Arrays.asList("A language", "James Gosling");

        subject = new Subject("Programming", questions, options, correctAnswers);
    }

    @Test
    void testQuestionsNotNull() {
        assertNotNull(subject.getQuestions());
    }

    @Test
    void testOptionsNotNull() {
        assertNotNull(subject.getOptions());
    }

    @Test
    void testCorrectAnswersNotNull() {
        assertNotNull(subject.getCorrectAnswers());
    }

    @Test
    void testQuestionListSize() {
        assertEquals(2, subject.getQuestions().size());
    }

    @Test
    void testOptionsListSize() {
        assertEquals(2, subject.getOptions().size());
    }

    @Test
    void testCorrectAnswersListSize() {
        assertEquals(2, subject.getCorrectAnswers().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Programming", "Mathematics"})
    void testParameterizedSubjectName(String name) {
        Subject testSubject = new Subject(name, subject.getQuestions(), subject.getOptions(), subject.getCorrectAnswers());
        assertEquals(name, testSubject.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "A language, A language",
            "James Gosling, James Gosling"
    })
    void testCorrectAnswersUsingCsv(String input, String expected) {
        assertEquals(expected, input);
    }

    @Test
    void testNotSameObjectReference() {
        Subject newSubject = new Subject("Math", subject.getQuestions(), subject.getOptions(), subject.getCorrectAnswers());
        assertNotSame(subject, newSubject);
    }

    @Test
    void testArrayEqualsForQuestions() {
        List<String> expected = Arrays.asList("What is Java?", "Who invented Java?");
        assertArrayEquals(expected.toArray(), subject.getQuestions().toArray());
    }

    @Test
    void testCorrectAnswers() {
        List<String> expected = Arrays.asList("A language", "James Gosling");
        assertArrayEquals(expected.toArray(), subject.getCorrectAnswers().toArray());
    }

    @Test
    @Timeout(1)
    void testPerformance() {
        assertDoesNotThrow(() -> subject.getQuestions().size());
    }
}
