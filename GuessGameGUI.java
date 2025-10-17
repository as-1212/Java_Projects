import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessGameGUI extends JFrame implements ActionListener {
    int numberToGuess;
    int attempts = 0;
    JTextField guessField;
    JLabel feedback;

    public GuessGameGUI() {
        setTitle(" Number Guessing Game");
        setSize(400, 250);
        setLayout(new GridLayout(4, 1, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel prompt = new JLabel("Guess the number between 1 and 100");
        prompt.setHorizontalAlignment(SwingConstants.CENTER);
        add(prompt);

        guessField = new JTextField();
        add(guessField);

        JButton guessBtn = new JButton("Guess!");
        guessBtn.addActionListener(this);
        add(guessBtn);

        feedback = new JLabel("Start guessing!", SwingConstants.CENTER);
        add(feedback);

        numberToGuess = new Random().nextInt(100) + 1;

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            if (guess == numberToGuess) {
                feedback.setText("🎉 Correct! Attempts: " + attempts);
                JOptionPane.showMessageDialog(this, "You guessed it in " + attempts + " tries!");
                numberToGuess = new Random().nextInt(100) + 1;
                attempts = 0;
            } else if (guess < numberToGuess) {
                feedback.setText("Too low! Try again.");
            } else {
                feedback.setText("Too high! Try again.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new GuessGameGUI();
    }
}
