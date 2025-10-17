import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class PasswordGeneratorApp extends JFrame {
    JTextField lengthField;
    JTextArea output;
    SecureRandom random = new SecureRandom();
    static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

    public PasswordGeneratorApp() {
        setTitle("🔐 Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Password Length:"));
        lengthField = new JTextField(5);
        top.add(lengthField);
        JButton generateBtn = new JButton("Generate");
        top.add(generateBtn);

        output = new JTextArea();
        output.setFont(new Font("Monospaced", Font.BOLD, 16));
        output.setEditable(false);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        generateBtn.addActionListener(e -> generatePassword());

        setVisible(true);
    }

    void generatePassword() {
        try {
            int len = Integer.parseInt(lengthField.getText());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            output.setText("Generated Password:\n" + sb.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new PasswordGeneratorApp();
    }
}
