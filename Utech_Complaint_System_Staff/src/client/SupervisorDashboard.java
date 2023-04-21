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

public class SupervisorDashboard extends JFrame {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	
	public SupervisorDashboard() {

        setTitle("Supervisor Dashboard");
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
        homeButton.setForeground(Color.WHITE); // Set text color to white
        homeButton.setBackground(new Color(0x2F2278)); // Set background color to #2F2278 (nazy blue)
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        homeButton.setMaximumSize(new Dimension(sideBarWidth, homeButton.getPreferredSize().height)); // Set maximum width
        menuBar.add(homeButton);

        JButton allComplaintsButton = new JButton("All Complaints");
        allComplaintsButton.setForeground(Color.WHITE); // Set text color to white
        allComplaintsButton.setBackground(new Color(0x2F2278)); // Set background color to #2F2278 (nazy blue)
        allComplaintsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        allComplaintsButton.setMaximumSize(new Dimension(sideBarWidth, allComplaintsButton.getPreferredSize().height)); // Set maximum width
        menuBar.add(allComplaintsButton);
        
        JButton studentComplaintsButton = new JButton("Student Complaints");
        studentComplaintsButton.setForeground(Color.WHITE); // Set text color to white
        studentComplaintsButton.setBackground(new Color(0x2F2278)); // Set background color to #2F2278 (nazy blue)
        studentComplaintsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        studentComplaintsButton.setMaximumSize(new Dimension(sideBarWidth, studentComplaintsButton.getPreferredSize().height)); // Set maximum width
        menuBar.add(studentComplaintsButton);
        
        JButton assignAdvisorButton = new JButton("Assign Advisor");
        assignAdvisorButton.setForeground(Color.WHITE); // Set text color to white
        assignAdvisorButton.setBackground(new Color(0x2F2278)); // Set background color to #2F2278 (nazy blue)
        assignAdvisorButton.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the button
        assignAdvisorButton.setMaximumSize(new Dimension(sideBarWidth, assignAdvisorButton.getPreferredSize().height)); // Set maximum width
        menuBar.add(assignAdvisorButton);
       
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

        allComplaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "allComplaintsPanel");
            }
        });

        studentComplaintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "studentComplaintsPanel");
            }
        });
        
        assignAdvisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "assignAdvisorPanel");
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
        JPanel allComplaintsPanel = createAllComplaintsPanel();
        JPanel studentComplaintsPanel = createStudentComplaintsPanel();
        JPanel assignAdvisorPanel = createAssignAdvisorPanel();

        mainPanel.add(homePanel, "homePanel");
        mainPanel.add(allComplaintsPanel, "allComplaintsPanel");
        mainPanel.add(studentComplaintsPanel, "studentComplaintsPanel");
        mainPanel.add(assignAdvisorPanel, "assignAdvisorPane");
    }
	
 // Home Panel
    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to the Student Home Page");
        homePanel.add(welcomeLabel);
        return homePanel;
    }
    
    private JPanel createAllComplaintsPanel() {
        JPanel allComplaintsPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("All complaints");
        allComplaintsPanel.add(welcomeLabel);
        return allComplaintsPanel;
    }
    
    private JPanel createStudentComplaintsPanel() {
        JPanel studentComplaintsPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Student Complaints");
        studentComplaintsPanel.add(welcomeLabel);
        return studentComplaintsPanel;
    }
    
    private JPanel createAssignAdvisorPanel() {
        JPanel assignAdvisorPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Assign Advisor");
        assignAdvisorPanel.add(welcomeLabel);
        return assignAdvisorPanel;
    }
    
    private void handleLogout() {
        int confirmationResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmationResult == JOptionPane.YES_OPTION) {
            // close the current window
            this.dispose();

            // shows the StaffAuthenticator
            StaffAuthenticator staffAuthenticator = new StaffAuthenticator();
            staffAuthenticator.setVisible(true);
            staffAuthenticator.setLocationRelativeTo(null);
        }
    }
  
}
