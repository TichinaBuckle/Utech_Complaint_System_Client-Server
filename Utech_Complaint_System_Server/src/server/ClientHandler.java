package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Account;
import domain.Complaint;
import domain.Protocol;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private AccountCRUD accountCrud;
    private ComplaintCRUD complaintCrud;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.accountCrud = new AccountCRUD();
        this.complaintCrud = new ComplaintCRUD();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message: " + inputLine);
                String response = processMessage(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    private String processMessage(String message) {
        String[] parts = message.split(":", 3);
        String messageType = parts[0];

        if (messageType.equals("AUTH_REQUEST")) {
            String id = parts[1];
            String password = parts[2];

            String role = AuthenticationHandler.authenticate(id, password);
            String response = Protocol.createAuthResponse(role != null, role);
            return response;
        } else if (messageType.equals("SIGN_UP_REQUEST")) {
            return handleSignUpRequest(message);
        } else if (messageType.equals("NEW_COMPLAINT_REQUEST")) {
            return handleNewComplaintRequest(message);
        } else if (messageType.equals("TRACK_COMPLAINT_REQUEST")) {
            return handleTrackComplaintRequest(message);
        }

        // Add more cases for other message types as needed.
        return "Unknown message type";
    }

    // Handles Sign Up
    private String handleSignUpRequest(String request) {
        String[] parts = request.split(":");

        String firstName = parts[1];
        String lastName = parts[2];
        String email = parts[3];
        String contactNumber = parts[4];
        String id = parts[5];
        String password = parts[6];
        String role = parts[7];

        // Create a new Account object
        Account account = new Account(id, firstName, lastName, email, contactNumber, password, role);

        // Store the account in the database using AccountCRUD
        boolean success = accountCrud.addAccount(account);

        return Protocol.createSignUpResponse(success);
    }

    // Handles New Complaint
    private String handleNewComplaintRequest(String request) {
        String[] parts = request.split(":");
        String studentId = parts[1];
        String category = parts[2];
        String description = parts[3];

        Complaint complaint = new Complaint();
        complaint.setStudentId(studentId);
        complaint.setStaffId(null); // Set advisor_id to null
        complaint.setCategory(category);
        complaint.setDescription(description);
        complaint.setStatus("open"); // Set status to "open"
        complaint.setCreateAt(new Date()); // Set created_at to the current date and time
        complaint.setUpdatedAt(new Date()); // Set updated_at to the current date and time

        boolean success = complaintCrud.addComplaint(complaint);
        String response = Protocol.createAuthResponse(success, ""); // error here "Protocol.createNewComplaintResponse(success)"
        return response;
    }
    
   // Handles Track Complaint
    private String handleTrackComplaintRequest(String request) {
        String[] parts = request.split(":");
        String studentId = parts[1];

        List<Complaint> complaints = complaintCrud.getComplaintsByStudentId(studentId);
        Map<Integer, String> advisorFullNames = new HashMap<>();
        for (Complaint complaint : complaints) {
            try {
                String advisorFullName = complaintCrud.getAdvisorFullName(complaint);
                advisorFullNames.put(complaint.getComplaintId(), advisorFullName);
            } catch (SQLException e) {
                System.err.println("Error getting advisor full name: " + e.getMessage());
                return Protocol.createAuthResponse(false, "");
            }
        }

        String response = Protocol.createTrackComplaintResponse(complaints, advisorFullNames);
        return response;
    }
    
    
}