import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    boolean isAvailable;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return title + " by " + author + " [" + (isAvailable ? "Available" : "Borrowed") + "]";
    }
}

public class LibraryGUI extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> bookList = new JList<>(listModel);

    public LibraryGUI() {
        setTitle("📚 Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sample books
        books.add(new Book("Harry Potter", "J.K. Rowling"));
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        refreshList();

        // Center panel
        add(new JScrollPane(bookList), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Book");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        buttonPanel.add(addBtn);
        buttonPanel.add(borrowBtn);
        buttonPanel.add(returnBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addBook());
        borrowBtn.addActionListener(e -> borrowBook());
        returnBtn.addActionListener(e -> returnBook());

        setVisible(true);
    }

    private void refreshList() {
        listModel.clear();
        for (Book b : books)
            listModel.addElement(b.toString());
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(this, "Enter book title:");
        if (title == null || title.isEmpty()) return;
        String author = JOptionPane.showInputDialog(this, "Enter author name:");
        if (author == null || author.isEmpty()) return;
        books.add(new Book(title, author));
        refreshList();
    }

    private void borrowBook() {
        int index = bookList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a book to borrow.");
            return;
        }
        Book b = books.get(index);
        if (b.isAvailable) {
            b.isAvailable = false;
            JOptionPane.showMessageDialog(this, "You borrowed: " + b.title);
        } else {
            JOptionPane.showMessageDialog(this, "Book already borrowed!");
        }
        refreshList();
    }

    private void returnBook() {
        int index = bookList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a book to return.");
            return;
        }
        Book b = books.get(index);
        if (!b.isAvailable) {
            b.isAvailable = true;
            JOptionPane.showMessageDialog(this, "You returned: " + b.title);
        } else {
            JOptionPane.showMessageDialog(this, "This book wasn’t borrowed.");
        }
        refreshList();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}
