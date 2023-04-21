/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package client;

import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Protocol;

public class StaffAuthenticator extends JFrame {
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    public StaffAuthenticator() {
        setTitle("Staff Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JLabel headingLabel = new JLabel("Staff Login");
        headingLabel.setFont(new Font(headingLabel.getFont().getName(), Font.BOLD, 24));
        idLabel = new JLabel("ID:");
        passwordLabel = new JLabel("Password:");
        idField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        // Set the preferred size for both buttons
        Dimension buttonSize = new Dimension(100, 25);
        loginButton.setPreferredSize(buttonSize);
        signUpButton.setPreferredSize(buttonSize);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStaffSignUp();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(4, 4, 4, 4);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(headingLabel, constraints);

        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(idLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(idField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        add(buttonPanel, constraints);
    }

    private void handleLogin() {
        String id = idField.getText();
        char[] password = passwordField.getPassword();
        String passwordString = new String(password);

        try {
            Client client = new Client("localhost", 12345);
            String authRequest = Protocol.createAuthRequest(id, passwordString);
            String authResponse = client.sendRequest(authRequest);
            client.close();

            boolean success = Protocol.isAuthResponseSuccess(authResponse);
            String role = Protocol.getAuthResponseRole(authResponse);

            if (success) {
                this.dispose(); // Close the login window
                if (role.equals("supervisor")) {
                    // Open the SupervisorDashboard
                    SupervisorDashboard supervisorDashboard = new SupervisorDashboard();
                    supervisorDashboard.setVisible(true);
                } else if (role.equals("advisor")) {
                    // Open the AdvisorDashboard
                    AdvisorDashboard advisorDashboard = new AdvisorDashboard();
                    advisorDashboard.setVisible(true);
                } else {
                    // Show an error message
                    JOptionPane.showMessageDialog(this, "Invalid login", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Show an error message
                JOptionPane.showMessageDialog(this, "Invalid login", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error connecting to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openStaffSignUp() {
        StaffSignUp staffSignUp = new StaffSignUp();
        staffSignUp.setVisible(true);
    }
}