/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package server;

public class Driver {

	public static void main(String[] args) {
		int port = 12345;
        Server server = new Server(port);
        server.start();
	}

}