package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import student_domain.Protocol;

public class AuthenticatorGUI extends JFrame {

    private JTextField idTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JPanel mainPanel;
    private Client client;

    public AuthenticatorGUI() {
        setTitle("Utech Complaint System - Login");
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        add(mainPanel);
        try {
            client = new Client("localhost", 12345);
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridBagLayout());

        idLabel = new JLabel("ID:");
        passwordLabel = new JLabel("Password:");
        idTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(idLabel, constraints);

        constraints.gridx = 1;
        mainPanel.add(idTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        mainPanel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(signUpButton, constraints);
    }

    private void handleLogin() {
        String id = idTextField.getText().trim();
        String password = new String(passwordField.getPassword());

        try {
            String loginRequest = Protocol.createLoginRequest(id, password);
            System.out.println("Sending login request: " + loginRequest); // Debug message
            String loginResponse = client.sendRequest(loginRequest);
            System.out.println("Received login response: " + loginResponse); // Debug message
            String[] responseParts = loginResponse.split(":", 3);

            if (responseParts[1].equals("SUCCESS")) {
                String role = responseParts[2];
                if (role.equals("student")) {
                    JOptionPane.showMessageDialog(this, "Login successful", "Login", JOptionPane.INFORMATION_MESSAGE);
                    // Open StudentDashboard
                    StudentDashboard studentDashboard = new StudentDashboard(id);
                    studentDashboard.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Login matches Staff Account. Switch to Staff Client", "Login", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, responseParts[2], "Login", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
        	System.err.println("Error occurred: " + e.getMessage());
        }
    }

    private void handleSignUp() {
        // Open StudentSignUp window
        StudentSignUp studentSignUp = new StudentSignUp();
        studentSignUp.setVisible(true);
    }
}