package Client;

import Shared.Constant;
import java.io.*;
import java.net.*;


public class Client {
	
	static Client ClientInstance = new Client();
	boolean clientstatus;
	String hostname = Constant.HOST_NAME;
    int port = Constant.PORT_NUMBER;
    int[] stream=new int[100];
    int no_of_channels=2;
    int frequency=2;
    int []values_received;

    // declaration section:
    // clientSocket: our client socket
    // os: output stream
    // is: input stream

    Socket clientSocket = null;
    DataOutputStream os = null;
    BufferedReader inFromServer = null;

	public boolean clientStatus() {
		return clientstatus;	
	}
	
	public static Client getClientInstance()
    {
        return ClientInstance;
    }
	
	public void startClient() {
		 // Initialization section:
        // Try to open a socket on the given port
        // Try to open input and output streams

        try {
            clientSocket = new Socket(hostname, port);
	    //sending data to server
            os = new DataOutputStream(clientSocket.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String sendMessage = frequency + "," + no_of_channels;
            bw.write(sendMessage);
            bw.flush();
	    
	    //receiving data from server
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = inFromServer.readLine();
            String[] stringArray = data.split(",");
             values_received = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
               String numberAsString = stringArray[i];
               values_received[i] = Integer.parseInt(numberAsString);
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
            System.err.println(e.getStackTrace());
        }

        // If everything has been initialized then we want to write some data
        // to the socket we have opened a connection to on the given port

        if (clientSocket == null || os == null || inFromServer == null) {
            System.err.println( "Something is wrong. One variable is null." );
            return;
        }

	}
	
	public int[] sendValuesToClientUI() {
		return values_received;
	}

	public void stopClient() {
		try {

            // clean up:
            os.close(); // close the output stream
            inFromServer.close();  // close the input stream
            clientSocket.close();  // close the socket

        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
	}
}

