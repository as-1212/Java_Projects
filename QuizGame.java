import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGame extends JFrame implements ActionListener {
    String[][] questions = {
        {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid", "A"},
        {"Which language runs in a browser?", "C++", "Java", "Python", "JavaScript", "D"},
        {"Who developed Java?", "Microsoft", "Sun Microsystems", "Google", "Apple", "B"}
    };
    int index = 0, score = 0;

    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg;
    JButton nextBtn;

    public QuizGame() {
        setTitle("🧠 Quiz Game");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionPanel = new JPanel(new GridLayout(4, 1));
        bg = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            bg.add(options[i]);
            optionPanel.add(options[i]);
        }
        add(optionPanel, BorderLayout.CENTER);

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(this);
        add(nextBtn, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        bg.clearSelection();
        if (index < questions.length) {
            questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[index][i + 1]);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your score: " + score);
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String correct = questions[index][5];
        String selected = "";
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = String.valueOf((char)('A' + i));
            }
        }
        if (selected.equals(correct))
            score++;

        index++;
        loadQuestion();
    }

    public static void main(String[] args) {
        new QuizGame();
    }
}
