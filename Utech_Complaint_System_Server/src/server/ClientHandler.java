package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import domain.Account;
import domain.Protocol;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private AccountCRUD accountCrud;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.accountCrud = new AccountCRUD();
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
}