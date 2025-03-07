import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class QuizGUI {

    private static final String TEACHER_USERNAME = "001";
    private static final String TEACHER_PASSWORD = "pass";
    private static final String STUDENT_USERNAME = "026";
    private static final String STUDENT_PASSWORD = "pass";

    private static final Map<String, Integer> leaderboard = new HashMap<>();
    static {
        leaderboard.put("231-134-009", 8);
        leaderboard.put("231-134-012", 6);
        leaderboard.put("231-134-002", 9);
        leaderboard.put("231-134-021", 7);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quiz App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            showRoleSelection(frame);
        });
    }

    private static void showRoleSelection(JFrame frame) {
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.setBackground(new Color(60, 63, 65)); // Dark background

        JLabel label = new JLabel("Select Role:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton instructorButton = createStyledButton("Instructor");
        JButton studentButton = createStyledButton("Student");

        instructorButton.addActionListener(_ -> showLoginScreen(frame));
        studentButton.addActionListener(_ -> showLoginScreen(frame));

        rolePanel.add(Box.createVerticalStrut(30));
        rolePanel.add(label);
        rolePanel.add(Box.createVerticalStrut(20));
        rolePanel.add(instructorButton);
        rolePanel.add(Box.createVerticalStrut(10));
        rolePanel.add(studentButton);

        frame.setContentPane(rolePanel);
        frame.setVisible(true);
    }

    private static void showLoginScreen(JFrame frame) {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(44, 47, 51));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = createStyledLabel("Username:");
        JTextField usernameField = new JTextField(12);
        JLabel passwordLabel = createStyledLabel("Password:");
        JPasswordField passwordField = new JPasswordField(12);
        JButton loginButton = createStyledButton("Login");
        JLabel loginMessage = createStyledLabel("");

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

        gbc.gridy = 3;
        loginPanel.add(loginMessage, gbc);

        frame.setContentPane(loginPanel);
        frame.revalidate();
        frame.repaint();

        loginButton.addActionListener((ActionEvent _) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(TEACHER_USERNAME) && password.equals(TEACHER_PASSWORD)) {
                createQuizScreen(frame);
            } else if (username.equals(STUDENT_USERNAME) && password.equals(STUDENT_PASSWORD)) {
                showSubjectSelection(frame);
            } else {
                loginMessage.setText("Invalid credentials.");
                loginMessage.setForeground(Color.RED);
            }
        });
    }

    private static void createQuizScreen(JFrame frame) {
        JPanel createQuizPanel = new JPanel();
        createQuizPanel.setLayout(new BoxLayout(createQuizPanel, BoxLayout.Y_AXIS));
        createQuizPanel.setBackground(new Color(45, 52, 54));

        JLabel createQuizLabel = createStyledLabel("Instructor Panel");
        JButton addQuestionButton = createStyledButton("Create Quiz");
        JButton leaderboardButton = createStyledButton("Quiz Result");
        JButton backButton = createStyledButton("Back to Login");

        addQuestionButton.addActionListener(_ -> showQuizCreationForm(frame));
        leaderboardButton.addActionListener(_ -> showLeaderboard(frame));
        backButton.addActionListener(_ -> showLoginScreen(frame));

        createQuizPanel.add(Box.createVerticalStrut(20));
        createQuizPanel.add(createQuizLabel);
        createQuizPanel.add(Box.createVerticalStrut(20));
        createQuizPanel.add(addQuestionButton);
        createQuizPanel.add(Box.createVerticalStrut(10));
        createQuizPanel.add(leaderboardButton);
        createQuizPanel.add(Box.createVerticalStrut(10));
        createQuizPanel.add(backButton);

        frame.setContentPane(createQuizPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void showLeaderboard(JFrame frame) {
        StringBuilder leaderboardText = new StringBuilder("<html><h2>Leaderboard</h2><br>");
        leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> leaderboardText.append(entry.getKey()).append(": ")
                        .append(entry.getValue()).append("<br>"));

        leaderboardText.append("</html>");
        JLabel leaderboardLabel = new JLabel(leaderboardText.toString(), SwingConstants.CENTER);
        JOptionPane.showMessageDialog(frame, leaderboardLabel, "Leaderboard",
                JOptionPane.INFORMATION_MESSAGE);
    }


   /*  private static void showInstructorPanel(JFrame frame) {
        JPanel instructorPanel = new JPanel();
        instructorPanel.setLayout(new BoxLayout(instructorPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Instructor Panel");
        JButton createQuizButton = new JButton("Create Quiz");
        JButton editQuizButton = new JButton("Edit/Delete Quiz");
        JButton backButton = new JButton("Logout");

        createQuizButton.addActionListener(_ -> showQuizCreationForm(frame));
       // editQuizButton.addActionListener(_ -> showQuizEditForm(frame));
        backButton.addActionListener(_ -> showLoginScreen(frame));

        instructorPanel.add(titleLabel);
        instructorPanel.add(createQuizButton);
        instructorPanel.add(editQuizButton);
        instructorPanel.add(backButton);

        frame.setContentPane(instructorPanel);
        frame.revalidate();
        frame.repaint();
    }*/



    private static void showQuizCreationForm(JFrame frame) {
        JPanel createPanel = new JPanel(new GridLayout(0, 1));

        JTextField quizTitleField = new JTextField(15);
        JTextField questionField = new JTextField(15);
        JTextField answerField = new JTextField(15);
        JButton saveButton = new JButton("Save Quiz");
        JButton backButton = new JButton("Back");

        createPanel.add(new JLabel("Quiz Title:"));
        createPanel.add(quizTitleField);
        createPanel.add(new JLabel("Question:"));
        createPanel.add(questionField);
        createPanel.add(new JLabel("Answer:"));
        createPanel.add(answerField);
        createPanel.add(saveButton);
        createPanel.add(backButton);

        frame.setContentPane(createPanel);
        frame.revalidate();
        frame.repaint();

        saveButton.addActionListener(_ -> {
            String quizTitle = quizTitleField.getText();
            String questionText = questionField.getText();
            String correctAnswer = answerField.getText();

            if (!quizTitle.isEmpty() && !questionText.isEmpty() && !correctAnswer.isEmpty()) {
                //QuizData.computeIfAbsent(quizTitle, k -> new ArrayList<>())
                //        .add(new Question(questionText, Collections.singletonList(correctAnswer), correctAnswer));
                JOptionPane.showMessageDialog(frame, "Quiz saved successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(_ -> createQuizScreen(frame));
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

        subjectPanel.add(Box.createVerticalStrut(30));
        subjectPanel.add(label);
        subjectPanel.add(Box.createVerticalStrut(20));
        subjectPanel.add(dsButton);
        subjectPanel.add(Box.createVerticalStrut(10));
        subjectPanel.add(algoButton);
        subjectPanel.add(Box.createVerticalStrut(10));
        subjectPanel.add(osButton);

        frame.setContentPane(subjectPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void takeQuiz(JFrame frame, String subject) {
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

        List<Question> questions = QuizData.getQuestions(subject);
        int score = 0;

        for (Question q : questions) {
            String selectedAnswer = (String) JOptionPane.showInputDialog(
                    frame,
                    q.getQuestionText(),
                    "Quiz - " + subject,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    q.getOptions().toArray(),
                    q.getOptions().get(0));

            if (selectedAnswer != null && selectedAnswer.equals(q.getCorrectAnswer())) {
                score++;
            }
        }

        leaderboard.put(STUDENT_USERNAME, score);
        JOptionPane.showMessageDialog(frame, "Your Score: " + score, "Quiz Completed", JOptionPane.INFORMATION_MESSAGE);
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