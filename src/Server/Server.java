/**
 * @SER516 Project2_Team05
 */

package Server;

import Shared.Constant;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Server class generates and sends random numbers to the client class according
 * to the highest and lowest value set at frequency set at server
 */

public class Server implements Runnable {
	private boolean ServerStatus;
	private BufferedWriter bufferWriter = null;
	private ServerSocket listener = null;
	private int highestValue = 1024;
	private int lowestValue = 0;
	private int frequency = 2;
	private int port = Constant.PORT_NUMBER;
	private String stringStream = "";
	Socket socket;

	/**
	 * The constructor will set the ServerStatus to true initially
	 **/
	public Server() {
		ServerStatus = true;
	}

	/**
	 * This method starts the server
	 **/
	@Override
	public void run() {
		this.StartServer();
	}

	/**
	 * setValues method will get the values from the text box of highestValue,
	 * lowestValue and frequency
	 * 
	 * @param highestValue
	 * @param lowestValue
	 * @param frequency
	 **/
	public void setValues(String highestValue, String lowestValue, String frequency) {
		if (highestValue != null) {
			this.highestValue = Integer.parseInt(highestValue);
		}
		if (lowestValue != null) {
			this.lowestValue = Integer.parseInt(lowestValue);
		}
		if (frequency != null) {
			this.frequency = Integer.parseInt(frequency);
		}
	}

	/**
	 * createThreadForServer creates a new server instance for the thread and start
	 * the thread with new server return the thread
	 * 
	 * @return server
	 **/
	public static Server createThreadForServer() {
		Server server = new Server();
		new Thread(server).start();
		return server;
	}

	/**
	 * checkServerStatus will return the current Server Status
	 * 
	 * @return this.ServerStatus
	 **/
	private boolean checkServerStatus() {
		return this.ServerStatus;
	}

	/**
	 * StopServer method will stop the server on button click and close the socket
	 * and listener
	 **/
	public synchronized void StopServer() {
		this.ServerStatus = false;
		try {
			if (this.socket != null) {
				this.socket.close();
			}
			this.listener.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			ServerConsole.getServerConsole().display("Error in Stopping server.");
		}
	}

	/**
	 * Server starts and sends random numbers to the client
	 */
	public void StartServer() {
		Random randomNumber = new Random();
		try {

			listener = new ServerSocket(port);
		} catch (Exception e)

		{
			System.out.print(e.getMessage());
		}

		try {
			socket = listener.accept();
		} catch (Exception e) {
			e.printStackTrace();
			ServerConsole.getServerConsole().display("Error: Unable to start server at port 1516.");
			return;
		}

		while (this.checkServerStatus()) {

			try {
				this.ServerStatus = false;
				try {
					OutputStream os = socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					bufferWriter = new BufferedWriter(osw);
					Thread thread = new Thread(new Runnable() {
						public void run() {
							try {
								while (true) {
									for (int j = 0; j < frequency; j++) {
										Integer valuesToBeSend = lowestValue
												+ randomNumber.nextInt(highestValue);
										stringStream += valuesToBeSend.toString() + ",";
									}
									stringStream += "\n";
									bufferWriter.write(stringStream);
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									bufferWriter.flush();
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					thread.start();

				} catch (Exception e) {
					e.printStackTrace();
					ServerConsole.getServerConsole().display(
							"Error 404 :Internal Server Error.Error in fetching server status.");
					return;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				ServerConsole.getServerConsole().display("Error 404 :Internal Server Error.");

			}
		}
	}
}
