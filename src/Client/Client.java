/**
 * @SER516 Project2_Team05
 */

package Client;

import Shared.Constant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Client class receives random values from the server at its frequency
 */

public class Client implements Runnable {

	static Client clientInstance = new Client();
	boolean clientStatus;
	int[] stream = new int[100];
	int noOfChannels = 2;
	int frequency = 2;
	String data = "";
	int numberReceived;
	ArrayList<Integer> valuesReceived = new ArrayList<Integer>();

	Socket clientSocket = null;
	BufferedReader inFromServer = null;

	/**
	 * This method starts the client
	 **/
	@Override
	public void run() {
		this.startClient();
	}

	/**
	 * createThreadForClient creates a new client instance for the thread and start
	 * the thread with new client return the thread
	 * 
	 * @return server
	 **/

	public static Client createThreadForClient() {
		Client client = new Client();
		new Thread(client).start();
		return client;
	}

	/**
	 * clientStatus will return the current Client Status
	 * 
	 * @return this.ServerStatus
	 **/

	public boolean clientStatus() {
		return clientStatus;
	}

	/**
	 * getClientInstance will return the client instance
	 * 
	 * @return this.clientInstance
	 **/

	public static Client getClientInstance() {
		return clientInstance;
	}

	/**
	 * Client starts and receives random values from server
	 */
	public void startClient() {
		try {
			InetAddress address = InetAddress.getByName(Constant.HOST_NAME);
			clientSocket = new Socket(address, Constant.PORT_NUMBER);

			InputStream is = clientSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			inFromServer = new BufferedReader(isr);

			while ((data = inFromServer.readLine()) != null) {
				String[] stringArray = data.split(",");
				for (int i = 0; i < stringArray.length; i++) {
					numberReceived = Integer.parseInt(stringArray[i]);
					valuesReceived.add(numberReceived);
				}
			}
		} catch (UnknownHostException e) {
			ClientConsole.getClientConsole()
					.display("Don't know about host: " + Constant.HOST_NAME);
		} catch (IOException e) {
			ClientConsole.getClientConsole()
					.display("Couldn't get I/O for the connection " + "to: " + Constant.HOST_NAME);
			ClientConsole.getClientConsole().display(e.getMessage());
		}

		if (clientSocket == null || inFromServer == null) {
			ClientConsole.getClientConsole().display("Something is wrong. One variable is null.");
			return;
		}
	}

	/**
	 * stopClient method will stop the client on button click and close the socket
	 * and listener
	 **/

	public synchronized void stopClient() {
		this.clientStatus = false;
		try {
			inFromServer.close();
			clientSocket.close();
		} catch (IOException e) {
			ClientConsole.getClientConsole().display(e.getMessage());
		}
	}

	public ArrayList<Integer> sendValuesToClientUI() {
		return valuesReceived;
	}

}
