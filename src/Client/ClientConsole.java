package Client;

import Shared.Console;

public class ClientConsole extends Console
{
  private static ClientConsole clientConsole = null;

   

	public static ClientConsole getClientConsole() {
        if (clientConsole == null) {
        	clientConsole = new ClientConsole();
        }
        return clientConsole;
    }
	
}