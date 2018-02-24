/**
 * @SER516 Project2_Team05
 */

package Server;

import Shared.Console;

/**
 * This class is used to create singleton object of server console Server
 * console is used to display messages on the server
 */

public class ServerConsole extends Console {

	private static ServerConsole serverConsole = null;

	public static ServerConsole getServerConsole() {
		if (serverConsole == null) {
			serverConsole = new ServerConsole();
		}
		return serverConsole;
	}
}
