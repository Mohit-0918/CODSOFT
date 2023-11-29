import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

public class QuizApplication extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private JLabel timerLabel; // Add timer label
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    // Arrays to store questions, choices, and correct answers
    private String[] questions = {
            "What is the capital of France?",
            "What is 2 + 2?",
            "What is the largest planet in our solar system?",
            "Which programming language is known for its 'write once, run anywhere' philosophy?",
            "Who is the author of 'To Kill a Mockingbird'?",
            "What is the powerhouse of the cell?",
            "Which element has the chemical symbol 'H'?",
            "What is the currency of Japan?",
            "Who painted the Mona Lisa?",
            "In which year did World War II end?"
    };

    private String[][] choices = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"3", "4", "5", "6"},
            {"Earth", "Jupiter", "Mars", "Venus"},
            {"Java", "C++", "Python", "JavaScript"},
            {"Harper Lee", "J.K. Rowling", "George Orwell", "Charles Dickens"},
            {"Mitochondria", "Nucleus", "Ribosome", "Endoplasmic Reticulum"},
            {"Hydrogen", "Helium", "Oxygen", "Nitrogen"},
            {"Won", "Yen", "Euro", "Dollar"},
            {"Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
            {"1945", "1950", "1939", "1960"}
    };

    private int[] correctAnswers = {2, 1, 1, 0, 0, 0, 0, 1, 0, 0};

    // Constructor for the QuizApplication class
    public QuizApplication() {
        // Set the title, default close operation, and size of the JFrame
        setTitle("Quiz Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLayout(new BorderLayout());

        // Initialize and add the question label to the JFrame
        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        // Create options panel with a grid layout and initialize radio buttons
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setText(choices[currentQuestionIndex][i]);
            buttonGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        // Add the options panel to the JFrame
        add(optionsPanel, BorderLayout.CENTER);

        // Initialize and add the submit button to the JFrame
        submitButton = new JButton("Submit");
        add(submitButton, BorderLayout.SOUTH);

        // ActionListener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        // Shuffle the questions
        java.util.List<String> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);
        questions = questionList.toArray(new String[0]);

        // Initialize and add the timer label to the JFrame
        timerLabel = new JLabel();
        add(timerLabel, BorderLayout.EAST); // Add the timer label

        // Initialize variables and start the timer
        currentQuestionIndex = 0;
        showQuestion();
        startTimer();
    }

    // Method to display the current question and options
    private void showQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);

        // Clear the selected option for each new question
        for (int i = 0; i < 4; i++) {
            options[i].setSelected(false);
            options[i].setText(choices[currentQuestionIndex][i]);
        }
    }

    // Method to move to the next question
    private void showNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            showQuestion();
            resetTimer();
        } else {
            showResult();
        }
    }

    // Method to check the selected answer against the correct answer
    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && i == correctAnswers[currentQuestionIndex]) {
                score++;
            }
        }
    }

    // Method to display the final quiz result
    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nScore: " + score + "/" + questions.length);
        System.exit(0);
    }

    // Method to start the timer
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() { // Timer ticks every 1 second
            private int secondsLeft = 5; // Initial timer value

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft >= 0) {
                    timerLabel.setText("Time left: " + secondsLeft + " seconds");
                    secondsLeft--;
                } else {
                    timer.stop();
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
        timer.start();
    }

    // Method to reset the timer
    private void resetTimer() {
        timer.stop();
        startTimer();
    }

    // Main method to create an instance of QuizApplication and make it visible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
