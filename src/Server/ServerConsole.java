package Server;


import javax.swing.*;


public class ServerConsole {



	JLabel consoleDisplay;

	private static ServerConsole console = null;

	public void print(String text) {

		this.consoleDisplay.setText(text);

	}
	
	public static ServerConsole getConsole() {

		if (null == console)

		{	console = new ServerConsole();}

		return console;	

	}

	public void setConsole(JLabel console) {

		this.consoleDisplay = console;

	}

}
