/**
 * @SER516 Project2_Team05
 */

package Client;

import Shared.Console;

/**
 * This class is used to create singleton object of server console Server
 * console is used to display messages on the server
 */

public class ClientConsole extends Console {
	private static ClientConsole clientConsole = null;

	public static ClientConsole getClientConsole() {
		if (clientConsole == null) {
			clientConsole = new ClientConsole();
		}
		return clientConsole;
	}

}