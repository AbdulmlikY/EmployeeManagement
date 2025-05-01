

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddEmployeeForm extends JFrame {

    private JTextField idField, nameField, deptField, salaryField;

    public AddEmployeeForm() {
        setTitle("Add Employee");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("Employee ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel deptLabel = new JLabel("Department:");
        JLabel salaryLabel = new JLabel("Salary:");

        idField = new JTextField();
        nameField = new JTextField();
        deptField = new JTextField();
        salaryField = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        add(idLabel); add(idField);
        add(nameLabel); add(nameField);
        add(deptLabel); add(deptField);
        add(salaryLabel); add(salaryField);
        add(saveButton); add(backButton);

        saveButton.addActionListener(e -> saveEmployee());
        backButton.addActionListener(e -> {
            new MainMenu().setVisible(true);
            this.dispose();
        });
    }

    private void saveEmployee() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String dept = deptField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || dept.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
            writer.write(id + "," + name + "," + dept + "," + salary);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Employee saved successfully.");
            idField.setText("");
            nameField.setText("");
            deptField.setText("");
            salaryField.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving employee.");
        }
    }
}
