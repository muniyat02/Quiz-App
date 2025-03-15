import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class QuizGUI {

    private static final String TEACHER_USERNAME = "001";
    private static final String TEACHER_PASSWORD = "pass";
    private static final String STUDENT_USERNAME = "026";
    private static final String STUDENT_PASSWORD = "pass";

    private static final Stack<JPanel> screenHistory = new Stack<>(); // Stack to manage screens

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quiz App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            showRoleSelection(frame);frame.setVisible(true);
        });
    }

    private static void showRoleSelection(JFrame frame) {

        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.setBackground(new Color(60, 63, 65));
        JLabel label = new JLabel("Select Role:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton instructorButton = createStyledButton("Instructor");
        JButton studentButton = createStyledButton("Student");

        instructorButton.addActionListener(_ -> showLoginScreen(frame, "Instructor"));
        studentButton.addActionListener(_ -> showLoginScreen(frame, "Student"));

        rolePanel.add(label);
        rolePanel.add(Box.createVerticalStrut(20));
        rolePanel.add(instructorButton);
        rolePanel.add(Box.createVerticalStrut(10));
        rolePanel.add(studentButton);

        frame.setContentPane(rolePanel);
        screenHistory.push(rolePanel); // Pushing current screen onto stack
        frame.revalidate();frame.repaint();
    }

    private static void showLoginScreen(JFrame frame, String role) {

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(44, 47, 51));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = createStyledLabel("Username:");
        JTextField usernameField = new JTextField(12);
        JLabel passwordLabel = createStyledLabel("Password:");
        JPasswordField passwordField = new JPasswordField(12);
        JButton loginButton = createStyledButton("Login");

        gbc.gridx = 0;gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);
        gbc.gridx = 0;gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 1;gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        // Create the back button
        JButton backButton = createBackButton(frame);
        gbc.gridx = 1;gbc.gridy = 3;
        loginPanel.add(backButton, gbc);

        frame.setContentPane(loginPanel);
        screenHistory.push(loginPanel); // Push login screen to the stack
        frame.revalidate();frame.repaint();

        // Action listener for login button
        loginButton.addActionListener((ActionEvent _) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (role.equals("Instructor") && username.equals(TEACHER_USERNAME) && password.equals(TEACHER_PASSWORD)) {
                JOptionPane.showMessageDialog(frame, "Logged in as Instructor!");
                showInstructorOptions(frame);
            } else if (role.equals("Student") && username.equals(STUDENT_USERNAME)
                    && password.equals(STUDENT_PASSWORD)) {
                JOptionPane.showMessageDialog(frame, "Logged in as Student!");
                showSubjectSelection(frame);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void showInstructorOptions(JFrame frame) {

        JPanel instructorPanel = new JPanel();
        instructorPanel.setLayout(new BoxLayout(instructorPanel, BoxLayout.Y_AXIS));
        instructorPanel.setBackground(new Color(40, 55, 71));
        JLabel label = createStyledLabel("Instructor Options:");
        JButton createQuizButton = createStyledButton("Create Quiz");
        JButton showLeaderboardButton = createStyledButton("Quiz Result");

        createQuizButton.addActionListener(_ -> showQuizCreation(frame));
        showLeaderboardButton.addActionListener(_ -> showLeaderboard(frame));

        instructorPanel.add(label);
        instructorPanel.add(Box.createVerticalStrut(20));
        instructorPanel.add(createQuizButton);
        instructorPanel.add(Box.createVerticalStrut(10));
        instructorPanel.add(showLeaderboardButton);

        // Add back button functionality
        JButton backButton = createBackButton(frame);
        instructorPanel.add(backButton);
        frame.setContentPane(instructorPanel);
        screenHistory.push(instructorPanel); // Push instructor panel to stack
        frame.revalidate();frame.repaint();
    }

    private static void showLeaderboard(JFrame frame) {

        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBackground(new Color(60, 63, 65));

        JLabel label = createStyledLabel("Quiz Result");
        JTextArea leaderboardArea = new JTextArea(10, 30);
        leaderboardArea.setText("1. 231-134-020 : 09\n2. 231-134-008 : 05\n3. 231-134-031 : 10");
        leaderboardPanel.add(label);
        leaderboardPanel.add(new JScrollPane(leaderboardArea));

        // Add back button functionality
        JButton backButton = createBackButton(frame); // Back Button
        leaderboardPanel.add(backButton);
        frame.setContentPane(leaderboardPanel);
        screenHistory.push(leaderboardPanel); // Push leaderboard panel to stack
        frame.revalidate();frame.repaint();
    }

    private static void showSubjectSelection(JFrame frame) {

        JPanel subjectPanel = new JPanel();
        subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.Y_AXIS));
        subjectPanel.setBackground(new Color(40, 55, 71));

        JLabel label = createStyledLabel("Select a Subject:");
        JButton dsButton = createStyledButton("Data Structure");
        JButton algoButton = createStyledButton("Algorithm");
        JButton osButton = createStyledButton("Operating System");

        dsButton.addActionListener(_ -> takeQuiz(frame, "Data Structure"));
        algoButton.addActionListener(_ -> takeQuiz(frame, "Algorithm"));
        osButton.addActionListener(_ -> takeQuiz(frame, "Operating System"));

        subjectPanel.add(label);
        subjectPanel.add(Box.createVerticalStrut(20));
        subjectPanel.add(dsButton);
        subjectPanel.add(Box.createVerticalStrut(10));
        subjectPanel.add(algoButton);
        subjectPanel.add(Box.createVerticalStrut(10));
        subjectPanel.add(osButton);

        // back button functionality
        JButton backButton = createBackButton(frame); // Back Button
        subjectPanel.add(backButton);
        frame.setContentPane(subjectPanel);
        screenHistory.push(subjectPanel); // Push subject panel to stack
        frame.revalidate();frame.repaint();
    }

    private static void showQuizCreation(JFrame frame) {

        JPanel createPanel = new JPanel(new GridLayout(0, 1));
        JLabel label = createStyledLabel("Create Quiz");
        JTextField quizTitleField = new JTextField(15);
        JTextField questionField = new JTextField(15);
        JTextField answerField = new JTextField(15);
        JButton saveButton = createStyledButton("Save Quiz");

        createPanel.add(label);
        createPanel.add(new JLabel("Quiz Title:"));
        createPanel.add(quizTitleField);
        createPanel.add(new JLabel("Question:"));
        createPanel.add(questionField);
        createPanel.add(new JLabel("Answer:"));
        createPanel.add(answerField);createPanel.add(saveButton);

        // Add back button functionality
        JButton backButton = createBackButton(frame); // Back Button
        createPanel.add(backButton);
        frame.setContentPane(createPanel);
        screenHistory.push(createPanel); // Push quiz creation panel to stack
        frame.revalidate();frame.repaint();

        saveButton.addActionListener(_ -> {
            JOptionPane.showMessageDialog(frame, "Quiz Saved!");
        });
    }

    private static void takeQuiz(JFrame frame, String subjectName) {
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        Subject subject = QuizData.getSubject(subjectName); // Get the specific subject
        int score = 0;
        List<String> questions = subject.getQuestions();
        List<List<String>> options = subject.getOptions();
        List<String> correctAnswers = subject.getCorrectAnswers();
        for (int i = 0; i < questions.size(); i++) {
            String question = questions.get(i);
            List<String> optionList = options.get(i);
            String correctAnswer = correctAnswers.get(i);

            String selectedAnswer = (String) JOptionPane.showInputDialog(frame, question, "Quiz - " + subjectName,
                    JOptionPane.QUESTION_MESSAGE, null, optionList.toArray(), optionList.get(0)); // Initial selected
                                                                                                  // option
            if (selectedAnswer != null && selectedAnswer.equals(correctAnswer)) {
                score++;
            }
        }
        JOptionPane.showMessageDialog(frame, "Quiz completed!\nYour Score: " + score + " / " + questions.size(),
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private static JButton createBackButton(JFrame frame) {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        backButton.addActionListener(_ -> {
            if (!screenHistory.isEmpty()) {
                screenHistory.pop(); // Pop the current screen
                JPanel previousScreen = screenHistory.peek(); // to Get the previous screen
                frame.setContentPane(previousScreen); // Set the previous screen as content
                frame.revalidate();
                frame.repaint();
            }
        });
        return backButton;
    }
    

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }
}