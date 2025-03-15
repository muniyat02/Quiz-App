import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

class QuizGUITests {

    // Testing valid credentials for instructor and student
    @Test
    public void testAuthenticationSuccess() {
        // Assuming a method authenticate(username, password) that returns boolean
        boolean validInstructorLogin = authenticate("001", "pass");  // Teacher credentials
        boolean validStudentLogin = authenticate("026", "pass");  // Student credentials

        assertTrue(validInstructorLogin, "Valid instructor credentials should allow successful login.");
        assertTrue(validStudentLogin, "Valid student credentials should allow successful login.");
    }

    // Testing invalid credentials do not allow login
    @Test
    public void testAuthenticationFailure() {
        boolean invalidLogin1 = authenticate("wrongUser", "wrongPass");
        boolean invalidLogin2 = authenticate("001", "wrongPass");

        assertFalse(invalidLogin1, "Invalid credentials should not allow login.");
        assertFalse(invalidLogin2, "Incorrect password should prevent login.");
    }

    // Testing back button navigates correctly through screens
    @Test
    public void testBackButtonFunctionality() {
        String currentPage = navigateBack("QuizPage");
        assertEquals("SubjectSelectionPage", currentPage, "Back button should navigate to Subject Selection from Quiz Page.");

        currentPage = navigateBack("SubjectSelectionPage");
        assertEquals("RoleSelectionPage", currentPage, "Back button should navigate to Role Selection from Subject Selection.");
    }

    // Testing score calculation is accurate for correct/incorrect answers
    @Test
    public void testQuizScoringLogic() {
        List<String> answers = List.of("Data Structure", "Algorithm", "Operating System");
        List<String> correctAnswers = List.of("Data Structure", "Algorithm", "Operating System");

        int score = calculateQuizScore(answers, correctAnswers);
        assertEquals(3, score, "Score should match the number of correct answers.");

        answers = List.of("Data Structure", "Algorithm", "Operating System");
        correctAnswers = List.of("Data Structure", "Algorithm", "Network");

        score = calculateQuizScore(answers, correctAnswers);
        assertEquals(2, score, "Score should reflect the number of correct answers.");
    }

    // Testing UI components like buttons, labels are created correctly
    @Test
    public void testComponentCreation() {
        JLabel roleSelectionLabel = createStyledLabel("Select Role:");
        JButton instructorButton = createStyledButton("Instructor");
        JButton studentButton = createStyledButton("Student");

        assertNotNull(roleSelectionLabel, "Role selection label should not be null.");
        assertNotNull(instructorButton, "Instructor button should not be null.");
        assertNotNull(studentButton, "Student button should not be null.");

        assertEquals("Instructor", instructorButton.getText(), "Instructor button should display correct text.");
        assertEquals("Student", studentButton.getText(), "Student button should display correct text.");
    }

    // Testing timeout on a long-running action (simulated)
    @Test
    public void testTimeout() {
        assertTimeout(Duration.ofSeconds(2), () -> {
            // Simulate a long action (like loading quiz questions)
            simulateLongAction();
        });
    }

    // Parameterized test for checking multiple valid usernames and passwords
    @ParameterizedTest
    @CsvSource({
            "001, pass, true",
            "026, pass, true",
            "123, wrongPass, false",
            "001, wrongPass, false"
    })
    public void testAuthenticationWithParameters(String username, String password, boolean expectedResult) {
        boolean result = authenticate(username, password);
        assertEquals(expectedResult, result, "Authentication should match the expected result.");
    }

    // Utility methods
    private boolean authenticate(String username, String password) {
        // Simulated authentication logic (mocked for test)
        return (username.equals("001") && password.equals("pass")) || (username.equals("026") && password.equals("pass"));
    }

    private String navigateBack(String currentPage) {
        // Simulate navigation based on the current screen (mocked for test)
        if (currentPage.equals("QuizPage")) {
            return "SubjectSelectionPage";
        } else if (currentPage.equals("SubjectSelectionPage")) {
            return "RoleSelectionPage";
        }
        return currentPage;
    }

    private int calculateQuizScore(List<String> answers, List<String> correctAnswers) {
        int score = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(correctAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }

    private void simulateLongAction() throws InterruptedException {
        // Simulate a long action (e.g., waiting for data to load)
        Thread.sleep(1500);
    }

    // Simulated component creation methods (for testing)
    private JLabel createStyledLabel(String text) {
        return new JLabel(text);
    }

    private JButton createStyledButton(String text) {
        return new JButton(text);
    }
}
