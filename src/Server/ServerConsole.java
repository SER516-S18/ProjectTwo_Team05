package Server;


import javax.swing.JTextPane;



public class ServerConsole {



	JTextPane consoleDisplay;

	private static ServerConsole console = null;

	public void print(String text) {

		this.consoleDisplay.setText(text);

	}
	
	public static ServerConsole getConsole() {

		if (null == console)

		{	console = new ServerConsole();}

		return console;	

	}

	public void setConsole(JTextPane console) {

		this.consoleDisplay = console;

	}

}
