package Server;


import javax.swing.JTextPane;



public class Console {



	JTextPane consoleDisplay;

	private static Console console = null;


	public Console() {

//add contsructor

	}
	
	public void print(String text) {

		this.consoleDisplay.setText("Message:" + text);

	}


}
