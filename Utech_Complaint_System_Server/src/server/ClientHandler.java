package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import domain.Protocol;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Authenticator authenticator;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.authenticator = new Authenticator();
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

        if (messageType.equals("LOGIN_REQUEST")) {
            String id = parts[1];
            String password = parts[2];

            // Create a map using a HashMap
            Map<String, String> parameters = new HashMap<>();
            parameters.put("id", id);
            parameters.put("password", password);

            String studentAuthResult = handleAuthenticateStudent(parameters);
            if (studentAuthResult.equals("SUCCESS")) {
                return Protocol.createLoginResponse(true, "SUCCESS:student");
            } else {
                String staffAuthResult = handleAuthenticateStaff(parameters);
                if (staffAuthResult.equals("SUCCESS")) {
                    return Protocol.createLoginResponse(true, "SUCCESS:staff");
                } else {
                    return Protocol.createLoginResponse(false, "FAILED:Invalid ID or password");
                }
            }
        }
        return "Unknown message type";
    }

    
    private String handleAuthenticateStudent(Map<String, String> parameters) {
        String studentId = parameters.get("id");
        String password = parameters.get("password");

        boolean isAuthenticated = authenticator.authenticateStudent(studentId, password);
        return isAuthenticated ? "SUCCESS" : "FAILED";
    }

    private String handleAuthenticateStaff(Map<String, String> parameters) {
        String staffId = parameters.get("id");
        String password = parameters.get("password");

        boolean isAuthenticated = authenticator.authenticateStaff(staffId, password);
        return isAuthenticated ? "SUCCESS" : "FAILED";
    }
}