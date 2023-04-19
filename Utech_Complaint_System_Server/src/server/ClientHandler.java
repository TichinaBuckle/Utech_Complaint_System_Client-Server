package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import domain.Protocol;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
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
            String email = parts[1];
            String password = parts[2];
            // Perform login logic here.
            boolean success = false; // Set this to the result of your login logic.
            String responseMessage = "Login failed"; // Set this to an appropriate message based on your login logic.
            return Protocol.createLoginResponse(success, responseMessage);
        }

        // Add more cases for other message types as needed.
        return "Unknown message type";
    }
}