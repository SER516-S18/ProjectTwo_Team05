/**
 * @SER516 Project2_Team05
 */

package Server;

import Shared.Constant;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 * ServerUI Class will set the UI for the server frame Start/Stop button will
 * help the server to start or stop the server The ServerStatusView will show
 * the blinker, if the server is working or not
 */

public class ServerUIMain {
	public static boolean serverRunning = false;
	Server serverThread;

	/**
	 * Constructor for ServerUI Set the Frames, Panels and Labels from the ServerUI
	 * Set the highest value, lowest value and frequency values in TextBox
	 * Start/Stop button to start or stop the server
	 */
	public ServerUIMain() {

		JFrame serverWindow = new JFrame();
		serverWindow.setTitle("Server");
		serverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverWindow.setBounds(100, 100, 800, 800);
		serverWindow.setVisible(true);
		serverWindow.setLayout(null);

		JTextPane consoleOutput = new JTextPane();
		consoleOutput.setBackground(Color.LIGHT_GRAY);
		consoleOutput.setBounds(10, 610, 760, 130);
		consoleOutput.setBorder(BorderFactory.createLineBorder(Color.black));
		ServerConsole servConsole = ServerConsole.getServerConsole();
		servConsole.setConsolePanel(consoleOutput);
		serverWindow.getContentPane().add(consoleOutput);
		serverWindow.add(consoleOutput);
		consoleOutput.setLayout(null);

		JButton startStop = new JButton(Constant.STARTSTOPLABELNAME);
		startStop.setBackground(Constant.LIGHTPINK);
		startStop.setBounds(580, 15, 190, 30);
		startStop.setBorder(BorderFactory.createLineBorder(Color.black));
		serverWindow.add(startStop);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.setBounds(10, 50, 760, 550);
		mainPanel.setLayout(null);
		serverWindow.add(mainPanel);

		ServerStatusView statusView = new ServerStatusView();
		statusView.setBounds(15, 15, 520, 520);
		mainPanel.add(statusView);

		JLabel highestValueLabel = new JLabel("<html>Highest<br>value:</html>");
		highestValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		highestValueLabel.setBackground(Constant.LIGHTBLUE);
		highestValueLabel.setBounds(560, 15, 85, 60);
		highestValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		highestValueLabel.setOpaque(true);
		mainPanel.add(highestValueLabel);

		JTextField highestValueTextBox = new JTextField();
		highestValueTextBox.setBorder(BorderFactory.createLineBorder(Color.black));
		highestValueTextBox.setBackground(Constant.LIGHTPINK);
		highestValueTextBox.setFont(Constant.INPUTNUMBERFONT);
		highestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		highestValueTextBox.setBounds(655, 15, 85, 60);
		mainPanel.add(highestValueTextBox);

		JLabel lowestValueLabel = new JLabel("<html>Lowest<br>value:</html>");
		lowestValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValueLabel.setBackground(Constant.LIGHTPINK);
		lowestValueLabel.setBounds(560, 90, 85, 60);
		lowestValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValueLabel.setOpaque(true);
		mainPanel.add(lowestValueLabel);

		JTextField lowestValueTextBox = new JTextField();
		lowestValueTextBox.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValueTextBox.setBackground(Constant.LIGHTBLUE);
		lowestValueTextBox.setFont(Constant.INPUTNUMBERFONT);
		lowestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValueTextBox.setBounds(655, 90, 85, 60);
		mainPanel.add(lowestValueTextBox);

		JLabel frequencyLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
		frequencyLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		frequencyLabel.setBackground(Constant.LIGHTBLUE);
		frequencyLabel.setBounds(560, 165, 85, 60);
		frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyLabel.setOpaque(true);
		mainPanel.add(frequencyLabel);

		JTextField frequencyValueTextBox = new JTextField();
		frequencyValueTextBox.setBorder(BorderFactory.createLineBorder(Color.black));
		frequencyValueTextBox.setBackground(Constant.LIGHTPINK);
		frequencyValueTextBox.setFont(Constant.INPUTNUMBERFONT);
		frequencyValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyValueTextBox.setBounds(655, 165, 85, 60);
		mainPanel.add(frequencyValueTextBox);

		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverRunning = !serverRunning;
				if (serverRunning) {
					if (!(highestValueTextBox.getText()).equals("")
							&& !(lowestValueTextBox.getText()).equals("")
							&& !(frequencyValueTextBox.getText()).equals("")) {
						ServerConsole.getServerConsole()
								.display("Server" + "is Running on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						serverThread.setValues(highestValueTextBox.getText(),
								lowestValueTextBox.getText(), frequencyValueTextBox.getText());
						statusView.running = true;
						statusView.statusBlinker.setForeground(Constant.COLORONBRIGHT);
					} else if ((highestValueTextBox.getText()).equals("")
							&& (lowestValueTextBox.getText()).equals("")
							&& (frequencyValueTextBox.getText()).equals("")) {
						ServerConsole.getServerConsole().display("Default Highest value , "
								+ "Lowest value and Frequency will be used. Server is Running "
								+ "on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(Constant.COLORONBRIGHT);
					} else if ((highestValueTextBox.getText()).equals("")) {
						ServerConsole.getServerConsole().display("HighestValue is not entered."
								+ "Default Value 1024 will be used as highest value. Server is Running on"
								+ "Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(Constant.COLORONBRIGHT);
					} else if ((lowestValueTextBox.getText()).equals("")) {
						ServerConsole.getServerConsole().display("LowestValue is not entered."
								+ "Default Value 0 will be used as Lowest value. Server is Running on "
								+ "Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(Constant.COLORONBRIGHT);
					} else if ((frequencyValueTextBox.getText()).equals("")) {
						ServerConsole.getServerConsole().display("Frequency is not entered."
								+ "Default Value 2 will be used for Frequency.Server is Running on Port"
								+ "Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(Constant.COLORONBRIGHT);
					}
				} else {
					statusView.running = false;
					statusView.statusBlinker.setForeground(Color.red);
					ServerConsole.getServerConsole()
							.display("Server is Stopped on Port Number 1516.");
					serverThread = getThreadForServer();
					serverThread.StopServer();
				}
			}
		});
		mainPanel.repaint();
		consoleOutput.repaint();
	}

	/**
	 * Set the server thread to the newly created thread
	 * 
	 * @param serverThread
	 */
	public void setThreadForServer(Server serverThread) {
		this.serverThread = serverThread;
	}

	/**
	 * Get newly created server thread
	 * 
	 * @return handle to the current server thread
	 */
	public Server getThreadForServer() {
		return this.serverThread;
	}

	public static void main(String[] args) {
		new ServerUIMain();
	}
}
