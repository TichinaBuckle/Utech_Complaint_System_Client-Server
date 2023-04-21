/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package client;

import javax.swing.SwingUtilities;

public class Driver {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StaffAuthenticator().setVisible(true);
        });
    }
}