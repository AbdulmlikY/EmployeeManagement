

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SearchEmployeeForm extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea;

    public SearchEmployeeForm() {
        setTitle("Search Employee");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        topPanel.add(new JLabel("Enter Employee ID:"), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JButton backButton = new JButton("Back");

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchEmployee());
        backButton.addActionListener(e -> {
            new MainMenu().setVisible(true);
            this.dispose();
        });
    }

    private void searchEmployee() {
        String targetId = searchField.getText().trim();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(targetId)) {
                    resultArea.setText("ID: " + parts[0] + "\nName: " + parts[1] + "\nDepartment: " + parts[2] + "\nSalary: " + parts[3]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                resultArea.setText("Employee not found.");
            }
        } catch (IOException ex) {
            resultArea.setText("Error reading file.");
        }
    }
}
