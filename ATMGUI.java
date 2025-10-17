import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMGUI extends JFrame implements ActionListener {
    double balance = 1000.0;
    JLabel label;
    JButton depositBtn, withdrawBtn, checkBtn;

    public ATMGUI() {
        setTitle("🏦 Simple ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Welcome! Choose an operation:");
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        checkBtn = new JButton("Check Balance");

        add(label);
        add(depositBtn);
        add(withdrawBtn);
        add(checkBtn);

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        checkBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBtn) {
            JOptionPane.showMessageDialog(this, "Your Balance: ₹" + balance);
        } else if (e.getSource() == depositBtn) {
            String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            if (input != null && !input.isEmpty()) {
                double amt = Double.parseDouble(input);
                if (amt > 0) {
                    balance += amt;
                    JOptionPane.showMessageDialog(this, "₹" + amt + " deposited successfully!");
                } else JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        } else if (e.getSource() == withdrawBtn) {
            String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
            if (input != null && !input.isEmpty()) {
                double amt = Double.parseDouble(input);
                if (amt > 0 && amt <= balance) {
                    balance -= amt;
                    JOptionPane.showMessageDialog(this, "₹" + amt + " withdrawn successfully!");
                } else JOptionPane.showMessageDialog(this, "Invalid or insufficient balance.");
            }
        }
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}
