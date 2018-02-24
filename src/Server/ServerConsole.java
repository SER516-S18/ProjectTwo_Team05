package Server;


import java.awt.*;
import javax.swing.*;

import Shared.Console;


public class ServerConsole extends Console{

	
	   private static ServerConsole serverConsole = null;

	   
	    public static ServerConsole getServerConsole() {
	       
	    	if (serverConsole == null)
	    	{
	            serverConsole = new ServerConsole();
	        }
	        return serverConsole;
	    }
	    


}
