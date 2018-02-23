package Client;


import javax.swing.JTextPane;

/*Koushik Kotamraju*/

public class Client_Console {



	JTextPane consoleDisplay;

	private static Console console = null;


	public Client_Console() {

//add contsructor

	}

public void print(String text) {

		this.consoleDisplay.setText("Message:" + text);

	}
	
public static Console getConsole() {

		if (null == console)

		{	console = new Console();}

		return console;	

	}