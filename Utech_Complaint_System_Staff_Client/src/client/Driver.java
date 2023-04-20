package client;

import java.io.IOException;

import javax.swing.SwingUtilities;

import domain.Protocol;

public class Driver {
	 public static void main(String[] args) {
	        try {
	            Client client = new Client("localhost", 12345);
	            String loginRequest = Protocol.createLoginRequest("user@example.com", "password123");
	            String loginResponse = client.sendRequest(loginRequest);
	            System.out.println("Received response: " + loginResponse);
	            client.close();
	        } catch (IOException e) {
	            System.err.println("Error occurred: " + e.getMessage());
	        }
	    	
	    }
}
