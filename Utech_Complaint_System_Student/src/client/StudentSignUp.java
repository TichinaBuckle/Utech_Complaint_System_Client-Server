/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package client;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Protocol;

public class StudentSignUp extends JFrame {
    private JLabel headerLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel contactNumberLabel;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField contactNumberField;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public StudentSignUp() {
        setTitle("Student Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        headerLabel = new JLabel("Student Sign Up");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        emailLabel = new JLabel("Email:");
        contactNumberLabel = new JLabel("Contact Number:");
        idLabel = new JLabel("ID:");
        passwordLabel = new JLabel("Password:");

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        contactNumberField = new JTextField(20);
        idField = new JTextField(20);
        passwordField = new JPasswordField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(headerLabel, constraints);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(firstNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(firstNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(lastNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(lastNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(emailLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(contactNumberLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        add(contactNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(idLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        add(idField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(submitButton, constraints);
    }

    private void handleSubmit() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String id = idField.getText();
        char[] password = passwordField.getPassword();
        String passwordString = new String(password);
        String role = "student";

        // Send the data to the server application and add it to the database
        try {
            Client client = new Client("localhost", 12345); // Replace "localhost" and port number with the actual server address and port
            // You will need to create a method in the Protocol class to format the data for the server
            String signUpRequest = Protocol.createSignUpRequest(firstName, lastName, email, contactNumber, id, passwordString, role);
            String signUpResponse = client.sendRequest(signUpRequest);
            client.close();

            boolean success = Protocol.isSignUpResponseSuccess(signUpResponse);

            if (success) {
                JOptionPane.showMessageDialog(this, "Account created successfully", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error connecting to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}