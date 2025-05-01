

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Employee Management System");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Employee");
        JButton searchButton = new JButton("Search Employee");

        add(addButton);
        add(searchButton);

        addButton.addActionListener(e -> {
            new AddEmployeeForm().setVisible(true);
            this.dispose();
        });

        searchButton.addActionListener(e -> {
            new SearchEmployeeForm().setVisible(true);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
