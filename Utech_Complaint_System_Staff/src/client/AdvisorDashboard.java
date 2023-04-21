/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdvisorDashboard extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private String advisorId;
	
	public AdvisorDashboard() {
		this.advisorId = advisorId; // sets the studentId for this instance

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

        JButton newResponseButton = new JButton("New Response");
        newResponseButton.setForeground(Color.WHITE); // set text color to white
        newResponseButton.setBackground(new Color(0x2F2278)); // set background color to #2F2278
        newResponseButton.setAlignmentX(Component.CENTER_ALIGNMENT); // center the button
        newResponseButton.setMaximumSize(new Dimension(sideBarWidth, newResponseButton.getPreferredSize().height)); // set maximum width
        menuBar.add(newResponseButton);
        
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

        newResponseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "newResponsePanel");
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
        JPanel newResponsePanel = createnewResponsePanel();
        JPanel viewResponsesPanel = createViewResponsesPanel();
        JPanel liveChatPanel = createLiveChatPanel();

        mainPanel.add(homePanel, "homePanel");
        mainPanel.add(trackComplaintsPanel, "trackComplaintsPanel");
        mainPanel.add(newResponsePanel, "newResponsePanel");
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

    // New Response
    private JPanel createnewResponsePanel() {
    	JPanel newResponsePanel = new JPanel(new BorderLayout());
        JLabel newComplaintHeading = new JLabel("New Response");
        newResponsePanel.add(newComplaintHeading, BorderLayout.NORTH);
        
        return newResponsePanel;
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
            StaffAuthenticator staffAuthenticator = new StaffAuthenticator();
            staffAuthenticator.setVisible(true);
            staffAuthenticator.setLocationRelativeTo(null);
        }
    }
    
    private void handleLiveChat() {
        
    }
}
