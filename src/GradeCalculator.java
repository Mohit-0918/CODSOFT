import javax.swing.*;
import java.awt.event.*;

// Class definition extending JFrame and implementing ActionListener
public class GradeCalculator extends JFrame implements ActionListener {

    // Member variables
    JLabel[] labels;
    JTextField[] marksTextFields;
    JTextField[] totalMarksTextFields; // New entry column for entering total marks
    JButton calculateButton;

    // Constructor
    public GradeCalculator() {
        labels = new JLabel[5];
        marksTextFields = new JTextField[5];
        totalMarksTextFields = new JTextField[5]; // New entry column

        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};

        // Loop to create labels and text fields for marks and total marks
        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel(subjects[i]);
            marksTextFields[i] = new JTextField();
            totalMarksTextFields[i] = new JTextField(); // New text field for total marks

            labels[i].setBounds(50, 50 + i * 50, 100, 30);
            marksTextFields[i].setBounds(160, 50 + i * 50, 100, 30);
            totalMarksTextFields[i].setBounds(270, 50 + i * 50, 100, 30); // Adjusted position for total marks
            add(labels[i]);
            add(marksTextFields[i]);
            add(totalMarksTextFields[i]); // Add new text field to the frame
        }

        // Create and configure the Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 350, 100, 30); // Adjusted position for the button
        calculateButton.addActionListener(this);
        add(calculateButton);

        // Configure the JFrame
        setTitle("Student Grade Calculator");
        setSize(400, 500); // Adjusted frame size
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ActionListener method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int totalTotalMarks = 0; // New variable for total total marks
            int numSubjects = 0;

            // Loop to calculate total marks and total total marks
            for (int i = 0; i < 5; i++) {
                String marksText = marksTextFields[i].getText();
                String totalMarksText = totalMarksTextFields[i].getText(); // New total marks entry
                if (!marksText.isEmpty() && !totalMarksText.isEmpty()) {
                    int marks = Integer.parseInt(marksText);
                    int totalMarksSubject = Integer.parseInt(totalMarksText);
                    totalMarks += marks;
                    totalTotalMarks += totalMarksSubject;
                    numSubjects++;
                }
            }

            // Adjusted calculation for average percentage
            double averagePercentage = (double) totalMarks / totalTotalMarks * 100;
            String grade = calculateGrade(averagePercentage);

            // Display the result using JOptionPane
            JOptionPane.showMessageDialog(this, "Total Marks: " + totalMarks +
                    "\nAverage Percentage: " + averagePercentage + "%" +
                    "\nGrade: " + grade);
        }
    }

    // Method to calculate the grade based on percentage
    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // Main method to instantiate the GradeCalculator object
    public static void main(String[] args) {
        new GradeCalculator();
    }
}
