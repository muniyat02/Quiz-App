import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class QuizDataTest {

    @Test
    void testGetSubject_ValidSubject() {
        Subject subject = QuizData.getSubject("Data Structure");
        assertNotNull(subject, "Subject should not be null");
        assertEquals("Data Structure", subject.getName(), "Subject name should match");
    }

    @Test
    void testGetSubject_InvalidSubject() {
        assertNull(QuizData.getSubject("Nonexistent"), "Should return null for invalid subject");
    }

    @Test
    void testSameSubjectReference() {
        Subject subject1 = QuizData.getSubject("Algorithm");
        Subject subject2 = QuizData.getSubject("Algorithm");
        assertSame(subject1, subject2, "Should return the same subject object");
    }

    @Test
    void testNotSameSubject() {
        Subject subject1 = QuizData.getSubject("Data Structure");
        Subject subject2 = QuizData.getSubject("Operating System");
        assertNotSame(subject1, subject2, "Different subjects should not be the same");
    }

    @Test
    void testSubjectListSize() {
        assertEquals(10, QuizData.getSubject("Algorithm").getQuestions().size(), "Subject should have questions");
    }

    @Test
    void testSubjectQuestionMismatch() {
        assertNotEquals(11, QuizData.getSubject("Algorithm").getQuestions().size(), "Algorithm should not have 10 questions");
    }

    @Test
    void testThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            QuizData.getSubject(null).getQuestions();
        }, "Null subject should throw an exception");
    }

    @Test
    void testBooleanConditions() {
        assertTrue(QuizData.getSubject("Operating System").getQuestions().size() > 5, "OS subject should have more than 5 questions");
        assertFalse(QuizData.getSubject("Operating System").getQuestions().isEmpty(), "Questions list should not be empty");
    }

    @Test
    void testArrayEquals() {
        String[] expected = {"What is a Stack?", "Which data structure uses FIFO?"};
        String[] actual = QuizData.getSubject("Data Structure").getQuestions().subList(0, 2).toArray(new String[0]);
        assertArrayEquals(expected, actual, "First two questions should match");
    }

    @Test
    void testLinesMatch() {
        assertLinesMatch(
                QuizData.getSubject("Algorithm").getQuestions().subList(0, 2),
                QuizData.getSubject("Algorithm").getQuestions().subList(0, 2),
                "Questions should match line by line"
        );
    }

    @Test
    void testMethodExecutionTime() {
        assertTimeout(Duration.ofMillis(100), () -> {
            QuizData.getSubject("Data Structure");
        }, "Method should execute within 100ms");
    }

    @ParameterizedTest
    @CsvSource({"Data Structure", "Algorithm", "Operating System"})
    void testValidSubjectsParameterized(String subjectName) {
        assertNotNull(QuizData.getSubject(subjectName), "Subject should exist");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Data Structure", "Algorithm", "Operating System"})
    void testSubjectNameValidity(String subjectName) {
        assertNotNull(QuizData.getSubject(subjectName), "Subject should not be null");
    }

  /*  @ParameterizedTest
    @CsvFileSource(resources = "/Subjects.csv", numLinesToSkip = 0)
    void testSubjectsFromCSV(String subjectName) {
        assertNotNull(QuizData.getSubject(subjectName), "CSV Subject should exist");
    }*/

    static Stream<String> provideSubjects() {
        return Stream.of("Data Structure", "Algorithm", "Operating System");
    }

    @ParameterizedTest
    @MethodSource("provideSubjects")
    void testSubjectsUsingMethodSource(String subjectName) {
        assertNotNull(QuizData.getSubject(subjectName), "Method Source Subject should exist");
    }
}
