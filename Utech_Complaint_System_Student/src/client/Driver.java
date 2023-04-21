package client;

import java.io.IOException;

import javax.swing.SwingUtilities;

import domain.Protocol;

public class Driver {
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	        new StudentAuthenticator().setVisible(true);
	    });
	}
}
