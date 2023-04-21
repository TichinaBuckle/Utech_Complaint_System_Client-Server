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
import domain.Pair;
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
                // loadTrackComplaintsData();
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
                // loadViewResponsesData();
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

        // Add filter UI components
        JPanel filterPanel = new JPanel(new FlowLayout());
        JComboBox<String> filterTypeComboBox = new JComboBox<>(new String[]{"id", "category", "status", "advisor"});
        JTextField filterTextField = new JTextField(20);
        filterPanel.add(new JLabel("Filter by:"));
        filterPanel.add(filterTypeComboBox);
        filterPanel.add(filterTextField);
        trackComplaintsPanel.add(filterPanel, BorderLayout.SOUTH);

     // Fetch the complaints from the database
        List<Complaint> complaints;
        Map<String, String> advisorFullNames;

        try {
            Client client = new Client("localhost", 12345);
            String trackComplaintRequest = Protocol.createTrackComplaintRequest(id);
            String trackComplaintResponse = client.sendRequest(trackComplaintRequest);
            client.close();

            Pair<List<Complaint>, Map<String, String>> parsedResponse = Protocol.parseTrackComplaintResponse(trackComplaintResponse);
            complaints = parsedResponse.getFirst();
            advisorFullNames = parsedResponse.getSecond();

            // Add these print statements
            System.out.println("Fetched complaints: " + complaints);
            System.out.println("Fetched advisor full names: " + advisorFullNames);
            
        } catch (IOException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
            JOptionPane.showMessageDialog(trackComplaintsPanel, "Error connecting to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            complaints = new ArrayList<>(); // initialize with an empty list if there was an error
            advisorFullNames = new HashMap<>();
        }
        // Create the table model and set it to the JTable
        complaintsTable = new JTable();
        ComplaintsTableModel tableModel = new ComplaintsTableModel(complaints, advisorFullNames); // error here
        complaintsTable.setModel(tableModel);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(complaintsTable.getModel());
        complaintsTable.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(complaintsTable);
        trackComplaintsPanel.add(scrollPane, BorderLayout.CENTER);

        // Add actionListener for the filter type combo box
        filterTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filterType = (String) filterTypeComboBox.getSelectedItem();
                String filterString = filterTextField.getText();
                applyComplaintsFilter(filterString, filterType);
            }
        });

        // Add documentListener for the filter text field
        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateFilter() {
                String filterType = (String) filterTypeComboBox.getSelectedItem();
                String filterString = filterTextField.getText();
                applyComplaintsFilter(filterString, filterType);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }
        });

        return trackComplaintsPanel;
    }
    
    private void applyComplaintsFilter(String filterString, String filterType) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) complaintsTable.getRowSorter();
        RowFilter<TableModel, Object> rowFilter = null;

        if (filterString != null && !filterString.isEmpty()) {
            switch (filterType) {
                case "id":
                    rowFilter = RowFilter.regexFilter(filterString, 0); // 0 is the column index for "id"
                    break;
                case "category":
                    rowFilter = RowFilter.regexFilter(filterString, 1); // 1 is the column index for "category"
                    break;
                case "status":
                    rowFilter = RowFilter.regexFilter(filterString, 2); // 2 is the column index for "status"
                    break;
                case "advisor":
                    rowFilter = RowFilter.regexFilter(filterString, 3); // 3 is the column index for "advisor"
                    break;
            }
        }

        if (sorter != null) {
            sorter.setRowFilter(rowFilter);
        }
    }
    
    private void updateComplaintsTable() {
        try {
            Client client = new Client("localhost", 12345);
            String trackComplaintRequest = Protocol.createTrackComplaintRequest(id);
            String trackComplaintResponse = client.sendRequest(trackComplaintRequest);
            client.close();

            Pair<List<Complaint>, Map<String, String>> parsedResponse = Protocol.parseTrackComplaintResponse(trackComplaintResponse);
            List<Complaint> complaints = parsedResponse.getFirst();
            Map<String, String> advisorFullNames = parsedResponse.getSecond();

            // Pass the complaints and advisorFullNames to the ComplaintsTableModel
            ComplaintsTableModel model = new ComplaintsTableModel(complaints, advisorFullNames); // error here
            complaintsTable.setModel(model);
        } catch (IOException ex) {
            System.err.println("Error occurred: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error connecting to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
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
