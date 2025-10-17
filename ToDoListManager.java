import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class ToDoListManager extends JFrame {
    DefaultListModel<String> model;
    JList<String> taskList;
    JTextField input;
    ArrayList<String> tasks;

    public ToDoListManager() {
        setTitle("📝 To-Do List Manager");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tasks = new ArrayList<>();
        model = new DefaultListModel<>();
        taskList = new JList<>(model);

        input = new JTextField();
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");
        JButton saveBtn = new JButton("Save Tasks");
        JButton loadBtn = new JButton("Load Tasks");

        JPanel top = new JPanel(new BorderLayout());
        top.add(input, BorderLayout.CENTER);
        top.add(addBtn, BorderLayout.EAST);

        JPanel bottom = new JPanel();
        bottom.add(delBtn);
        bottom.add(saveBtn);
        bottom.add(loadBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String task = input.getText().trim();
            if (!task.isEmpty()) {
                model.addElement(task);
                tasks.add(task);
                input.setText("");
            }
        });

        delBtn.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                tasks.remove(index);
                model.remove(index);
            }
        });

        saveBtn.addActionListener(e -> saveTasks());
        loadBtn.addActionListener(e -> loadTasks());

        setVisible(true);
    }

    void saveTasks() {
        try (PrintWriter writer = new PrintWriter("tasks.txt")) {
            for (String t : tasks)
                writer.println(t);
            JOptionPane.showMessageDialog(this, "Tasks saved!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void loadTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            model.clear();
            tasks.clear();
            while ((line = br.readLine()) != null) {
                tasks.add(line);
                model.addElement(line);
            }
            JOptionPane.showMessageDialog(this, "Tasks loaded!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No saved tasks found.");
        }
    }

    public static void main(String[] args) {
        new ToDoListManager();
    }
}
