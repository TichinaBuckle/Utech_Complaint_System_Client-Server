/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import domain.Complaint;
import domain.Protocol;

public class StudentDashboard extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private String id;
	private JTable complaintsTable;
	
	public StudentDashboard(String id) {
		this.id = id; // sets the studentId for this instance

        setTitle("Student Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createMenuBar();
        createMainPanel();

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
	
	// setup the side bar / menu bar
    private void createMenuBar() {
        int sideBarWidth = 200; // set the width of the side bar

        JPanel menuBar = new JPanel();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
        menuBar.setBackground(new Color(0x2F2278)); // set background color to #2F2278 (nazy blue)
        menuBar.setPreferredSize(new Dimension(sideBarWidth, getHeight())); // Set preferred size for the menuBar

        JButton homeButton = new JButton("Home");
        homeButton.setForeground(Color.YELLOW); // Set text color to white
        homeButton.setBackground(new Color(0x2F2278)); // Set background color to #2F2278 (nazy blue)
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        homeButton.setMaximumSize(new Dimension(sideBarWidth, homeButton.getPreferredSize().height)); // Set maximum width
        menuBar.add(homeButton);

        JButton trackComplaintsButton = new JButton("Track Complaints");
        trackComplaintsButton.setForeground(Color.WHITE); // sets text color to white
        trackComplaintsButton.setBackground(new Color(0x2F2278)); // sets background color to #2F2278
        trackComplaintsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        trackComplaintsButton.setMaximumSize(new Dimension(sideBarWidth, trackComplaintsButton.getPreferredSize().height)); // set maximum width
        menuBar.add(trackComplaintsButton);

        JButton newComplaintButton = new JButton("New Complaint");
        newComplaintButton.setForeground(Color.WHITE); // set text color to white
        newComplaintButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        newComplaintButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        newComplaintButton.setMaximumSize(new Dimension(sideBarWidth, newComplaintButton.getPreferredSize().height)); // set maximum width
        menuBar.add(newComplaintButton);
        
        JButton viewResponsesButton = new JButton("View Responses");
        viewResponsesButton.setForeground(Color.WHITE); // set text color to white
        viewResponsesButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        viewResponsesButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        viewResponsesButton.setMaximumSize(new Dimension(sideBarWidth, viewResponsesButton.getPreferredSize().height)); // set maximum width
        menuBar.add(viewResponsesButton);
        
        JButton addResponseButton = new JButton("Add Response");
        addResponseButton.setForeground(Color.WHITE); // set text color to white
        addResponseButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        addResponseButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        addResponseButton.setMaximumSize(new Dimension(sideBarWidth, addResponseButton.getPreferredSize().height)); // set maximum width
        menuBar.add(addResponseButton);
        
        JButton liveChatButton = new JButton("Live Chat");
        liveChatButton.setForeground(Color.WHITE); // set text color to white
        liveChatButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        liveChatButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        liveChatButton.setMaximumSize(new Dimension(sideBarWidth, liveChatButton.getPreferredSize().height)); // set maximum width
        menuBar.add(liveChatButton);
       
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE); // set text color to white
        logoutButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        logoutButton.setMaximumSize(new Dimension(sideBarWidth, logoutButton.getPreferredSize().height)); // set maximum width
        menuBar.add(logoutButton);

        getContentPane().add(menuBar, BorderLayout.WEST);
        
        // listeners
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "homePanel");
            }
        });

        trackComplaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "trackComplaintsPanel");
            }
        });

        newComplaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "newComplaintPanel");
            }
        });
        
        viewResponsesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "viewResponsesPanel");
            }
        });
        
        addResponseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "addResponsePanel");
            }
        });

        liveChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "liveChatPanel");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });
    }
    
    private void createMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel homePanel = createHomePanel();
        JPanel trackComplaintsPanel = createTrackComplaintsPanel();
        JPanel newComplaintPanel = createNewComplaintPanel();
        JPanel viewResponsesPanel = createViewResponsesPanel();
        JPanel liveChatPanel = createLiveChatPanel();

        mainPanel.add(homePanel, "homePanel");
        mainPanel.add(trackComplaintsPanel, "trackComplaintsPanel");
        mainPanel.add(newComplaintPanel, "newComplaintPanel");
        mainPanel.add(viewResponsesPanel, "viewResponsesPanel");
        mainPanel.add(liveChatPanel, "liveChatPanel");
    }
	
    // Home Panel
    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to the Student Home Page");
        homePanel.add(welcomeLabel);
        return homePanel;
    }
    
	// Track Complaints
    private JPanel createTrackComplaintsPanel() {
        JPanel trackComplaintsPanel = new JPanel(new BorderLayout());
        JLabel trackComplaintsHeading = new JLabel("Track Complaints");
        trackComplaintsPanel.add(trackComplaintsHeading, BorderLayout.NORTH);

        return trackComplaintsPanel;
    }

    
    // New Complaint
    private JPanel createNewComplaintPanel() {
        JPanel newComplaintPanel = new JPanel(new BorderLayout());
        JLabel newComplaintHeading = new JLabel("New Complaints");
        newComplaintPanel.add(newComplaintHeading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        JComboBox<String> categoryDropdown = new JComboBox<>();
        categoryDropdown.addItem("general query");
        categoryDropdown.addItem("general complaint");
        categoryDropdown.addItem("module selection");
        categoryDropdown.addItem("missing grade");
        categoryDropdown.addItem("financial");
        JTextArea descriptionField = new JTextArea();
        JButton submitButton = new JButton("Submit");

        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryDropdown);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);

        newComplaintPanel.add(formPanel, BorderLayout.CENTER);

        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        submitButtonPanel.add(submitButton);
        newComplaintPanel.add(submitButtonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = id;
                String category = (String) categoryDropdown.getSelectedItem();
                String description = descriptionField.getText();

                // Send the data to the server application and add it to the database
                try {
                    Client client = new Client("localhost", 12345);
                    String newComplaintRequest = Protocol.createNewComplaintRequest(studentId, category, description);
                    String newComplaintResponse = client.sendRequest(newComplaintRequest);
                    client.close();

                    boolean success = Protocol.isNewComplaintResponseSuccess(newComplaintResponse);

                    if (success) {
                        JOptionPane.showMessageDialog(newComplaintPanel, "Complaint submitted successfully.");
                        categoryDropdown.setSelectedIndex(0);
                        descriptionField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(newComplaintPanel, "Error submitting complaint. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    System.err.println("Error occurred: " + ex.getMessage());
                    JOptionPane.showMessageDialog(newComplaintPanel, "Error connecting to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return newComplaintPanel;
    }
    
    // View Responses
    private JPanel createViewResponsesPanel() {
    	JPanel viewResponsesPanel = new JPanel(new BorderLayout());
        JLabel viewResponsesHeading = new JLabel("View Responses");
        viewResponsesPanel.add(viewResponsesHeading, BorderLayout.NORTH);
        
        return viewResponsesPanel;
    }
    
    // Create Responses
    private JPanel createAddResponsePanel() {
    	JPanel addResponsePanel = new JPanel(new BorderLayout());
        JLabel addResponseHeading = new JLabel("Add Response");
        addResponsePanel.add(addResponseHeading, BorderLayout.NORTH);
        
        return addResponsePanel;
    }

    // Live Chat
    private JPanel createLiveChatPanel() {
        JPanel liveChatPanel = new JPanel();
        // adds components and logic for live chat
        
        return liveChatPanel;
    }
    
    private void handleLogout() {
        int confirmationResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmationResult == JOptionPane.YES_OPTION) {
            // close the current window
            this.dispose();

            // shows the StudentAuthenticator
            StudentAuthenticator studentAuthenticator = new StudentAuthenticator();
            studentAuthenticator.setVisible(true);
            studentAuthenticator.setLocationRelativeTo(null);
        }
    }
    
    private void handleLiveChat() {
        
    }
}
