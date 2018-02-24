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
import javax.swing.SwingConstants;

/**
 * ServerUI Class will set the UI for the server frame
 * Start/Stop button will help the server to start or stop the server
 * The ServerStatusView will show the blinker, if the server is working or not 
 */

public class ServerUIMain {
	public static Color LIGHTBLUE = Constant.LIGHTBLUE;
	public static Color LIGHTPINK = Constant.LIGHTPINK;
	public static boolean ServerRunning = false;
	private ServerConsole console;
	private final Color COLOR_OFF = Color.red;
	private final Color COLOR_ON_BRIGHT = new Color(168, 208, 141);
	Server serverThread;

	/**
	 * Constructor for ServerUI
	 * Set the Frames, Panels and Labels from the ServerUI
	 * Set the highest value, lowest value and frequency values in TextBox
	 * Start/Stop button to start or stop the server
	 */
	public ServerUIMain() {
		
		JFrame serverWindow = new JFrame();
		console = new ServerConsole();
		serverWindow.setTitle("Server");
		serverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverWindow.setBounds(100, 100, 800, 800);
		serverWindow.setVisible(true);
		serverWindow.setLayout(null);

		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		consolePanel.setBackground(Color.lightGray);
		consolePanel.setBounds(10, 610, 760, 130);
		consolePanel.setLayout(null);
		serverWindow.add(consolePanel);

		JLabel consoleLabel = new JLabel(Constant.CONSOLENAME);
		consoleLabel.setBounds(5, 10, 80, 10);
		consolePanel.add(consoleLabel);

		JLabel consoleMessage = new JLabel();
		consoleMessage.setBounds(70, 5, 1000, 40);
		consoleMessage.setBackground(Color.lightGray);
		consolePanel.add(consoleMessage);

		JButton startStop = new JButton(Constant.STARTSTOPLABELNAME);
		startStop.setBackground(LIGHTPINK);
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
		highestValueLabel.setBackground(LIGHTBLUE);
		highestValueLabel.setBounds(560, 15, 85, 60);
		highestValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		highestValueLabel.setOpaque(true);
		mainPanel.add(highestValueLabel);

		JTextField highestValueTextBox = new JTextField();
		highestValueTextBox.setBorder(BorderFactory.createLineBorder(Color.black));
		highestValueTextBox.setBackground(LIGHTPINK);
		highestValueTextBox.setFont(Constant.INPUTNUMBERFONT);
		highestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		highestValueTextBox.setBounds(655, 15, 85, 60);
		mainPanel.add(highestValueTextBox);

		JLabel lowestValueLabel = new JLabel("<html>Lowest<br>value:</html>");
		lowestValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValueLabel.setBackground(LIGHTPINK);
		lowestValueLabel.setBounds(560, 90, 85, 60);
		lowestValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValueLabel.setOpaque(true);
		mainPanel.add(lowestValueLabel);

		JTextField lowestValueTextBox = new JTextField();
		lowestValueTextBox.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValueTextBox.setBackground(LIGHTBLUE);
		lowestValueTextBox.setFont(Constant.INPUTNUMBERFONT);
		lowestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValueTextBox.setBounds(655, 90, 85, 60);
		mainPanel.add(lowestValueTextBox);

		JLabel frequencyLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
		frequencyLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		frequencyLabel.setBackground(LIGHTBLUE);
		frequencyLabel.setBounds(560, 165, 85, 60);
		frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyLabel.setOpaque(true);
		mainPanel.add(frequencyLabel);

		JTextField frequencyValueTextBox = new JTextField();
		frequencyValueTextBox.setBorder(BorderFactory.createLineBorder(Color.black));
		frequencyValueTextBox.setBackground(LIGHTPINK);
		frequencyValueTextBox.setFont(Constant.INPUTNUMBERFONT);
		frequencyValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyValueTextBox.setBounds(655, 165, 85, 60);
		mainPanel.add(frequencyValueTextBox);

		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerRunning = !ServerRunning;
				if (ServerRunning) {
					if (!(highestValueTextBox.getText()).equals("") 
							&& !(lowestValueTextBox.getText()).equals("") 
							&& !(frequencyValueTextBox.getText()).equals("")) {
						console.setConsole(consoleMessage);
						console.print("Server is Running on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						serverThread.setValues(highestValueTextBox.getText(), 
								lowestValueTextBox.getText(), frequencyValueTextBox.getText());
						statusView.running = true;
						statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
					} else if ((highestValueTextBox.getText()).equals("") 
							&& (lowestValueTextBox.getText()).equals("") 
							&& (frequencyValueTextBox.getText()).equals("")) {
						console.setConsole(consoleMessage);
						console.print(
								"Default Highest value , Lowest value and Frequency will "
								+ "be used. Server is Running on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
					} else if ((highestValueTextBox.getText()).equals("")) {
						console.setConsole(consoleMessage);
						console.print(
								"HighestValue is not entered. Default Value 1024 will be "
								+ "used as highest value. Server is Running on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
					} else if ((lowestValueTextBox.getText()).equals("")) {
						console.setConsole(consoleMessage);
						console.print(
								"LowestValue is not entered. Default Value 0 will be used "
								+ "as Lowest value. Server is Running on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
					} else if ((frequencyValueTextBox.getText()).equals("")) {
						console.setConsole(consoleMessage);
						console.print(
								"Frequency is not entered. Default Value 2 will be used for"
								+ " Frequency.Server is Running on Port Number 1516.");
						setThreadForServer(Server.createThreadForServer());
						statusView.running = true;
						statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
					}
				} else {
					statusView.running = false;
					statusView.statusBlinker.setForeground(COLOR_OFF);
					console.setConsole(consoleMessage);
					console.print("Server is Stopped on Port Number 1516.");
					serverThread = getThreadForServer();
					serverThread.StopServer();
				}
			}
		});
		mainPanel.repaint();
		consolePanel.repaint();
	}

	/**
	 * Set the server thread to the newly created thread
	 * @param serverThread
	 */
	public void setThreadForServer(Server serverThread) {
		this.serverThread = serverThread;
	}

	/**
	 * Get newly created server thread
	 * @return handle to the current server thread
	 */
	public Server getThreadForServer() {
		return this.serverThread;
	}

	public static void main(String[] args) {
		ServerUIMain serverInstance = new ServerUIMain();
	}
}
