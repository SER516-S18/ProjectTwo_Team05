/**
 * @SER516 Project2_Team05
 */

package Server;

import Shared.Console;

public class ServerConsole extends Console {

	private static ServerConsole serverConsole = null;

	public static ServerConsole getServerConsole() {
		if (serverConsole == null) {
			serverConsole = new ServerConsole();
		}
		return serverConsole;
	}
}
