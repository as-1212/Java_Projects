import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculatorGUI extends JFrame implements ActionListener {
    JTextField[] marks = new JTextField[5];
    JButton calcButton;
    JTextArea resultArea;

    public GradeCalculatorGUI() {
        setTitle("🎓 Student Grade Calculator");
        setSize(400, 400);
        setLayout(new GridLayout(8, 2, 5, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int i = 0; i < 5; i++) {
            add(new JLabel("Subject " + (i + 1) + " Marks:"));
            marks[i] = new JTextField();
            add(marks[i]);
        }

        calcButton = new JButton("Calculate Grade");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(calcButton);
        add(new JLabel(""));
        add(new JLabel("Result:"));
        add(resultArea);

        calcButton.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int total = 0;
            for (JTextField field : marks) {
                int m = Integer.parseInt(field.getText());
                if (m < 0 || m > 100) throw new Exception();
                total += m;
            }
            double avg = total / 5.0;
            char grade;
            if (avg >= 90) grade = 'A';
            else if (avg >= 75) grade = 'B';
            else if (avg >= 60) grade = 'C';
            else if (avg >= 40) grade = 'D';
            else grade = 'F';

            resultArea.setText("Total: " + total + "\nAverage: " + avg + "\nGrade: " + grade);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid marks (0-100).");
        }
    }

    public static void main(String[] args) {
        new GradeCalculatorGUI();
    }
}
