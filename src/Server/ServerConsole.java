package Server;


import java.awt.*;
import javax.swing.*;


/**
 * @author Abhishek Kumar
 *
 */
public class ServerConsole {


	JLabel consoleMessage;
	JPanel consolePanel;
	JLabel consoleLabel;

	public ServerConsole()
	{
		consolePanel = new JPanel();
		consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		consolePanel.setBackground(Color.lightGray);
		consolePanel.setBounds(10, 610, 760, 130);

		consoleLabel = new JLabel(" Console :");
		consoleLabel.setBounds(5, 10, 80, 10);
		consolePanel.add(consoleLabel);

		consoleMessage = new JLabel();
		consoleMessage.setBounds(70, 5, 1000, 40);
		consoleMessage.setBackground(Color.lightGray);
		consolePanel.add(consoleMessage);
		consolePanel.repaint();

	}
	public void setConsoleMessage(String text) {

		this.consoleMessage.setText(text);

	}


	public JPanel getConsolePanel() {

		return this.consolePanel;

	}


}
