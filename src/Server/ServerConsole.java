/**
 * @SER516 Project2_Team05
 */

package Server;

import javax.swing.JLabel;

/**
 * ServerConsole class is used to display the messages on the console
 **/
public class ServerConsole {

	private static ServerConsole console = null;
	JLabel consoleDisplay;

	/**
	 * print method is printing the value to the console 
	 * @param text to be print
	 **/
	public void print(String text) {
		this.consoleDisplay.setText(text);
	}

	/**
	 * ServerConsole method is creating a new instance of the class if we have not created
	 * @return console
	 **/
	public static ServerConsole getConsole() {
		if (null == console) {
			console = new ServerConsole();
		}
		return console;
	}

	/**
	 * setConsole method sets the newly created console to the this.consoleDisplay
	 * @param console
	 **/
	public void setConsole(JLabel console) {
		this.consoleDisplay = console;
	}
}
