/*
 * @SER516 PorjectTwo Team5
 * */
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


public class Client implements Runnable{
	
	static Client ClientInstance = new Client();
	boolean clientstatus;
	String hostname = Constant.HOST_NAME;
    int port = Constant.PORT_NUMBER;
    int[] stream=new int[100];
    int no_of_channels=2;
    int frequency=2;
    String data = "";
    ArrayList<Integer> values_received = new ArrayList<Integer>();

    // declaration section:
    // clientSocket: our client socket
    // os: output stream
    // is: input stream

    Socket clientSocket = null;
    BufferedReader inFromServer = null;

    @Override
    public void run(){
        this.startClient();
    }

    public static Client createThreadForClient()
    {
        Client client = new Client();
        new Thread(client).start();
        return client;
    }
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
        	 	InetAddress address = InetAddress.getByName(hostname);
            clientSocket = new Socket(address, port);
    			
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            inFromServer = new BufferedReader(isr);
            
            while ((data = inFromServer.readLine()) != null) {
                String[] stringArray = data.split(",");
                System.out.println(data);
                for (int i = 0; i < stringArray.length; i++) {
                   Integer numberReceived = Integer.parseInt(stringArray[i]);
                   values_received.add(numberReceived);
                }
            }
        System.out.println(values_received);

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
            System.err.println(e.getStackTrace());
        }

        // If everything has been initialized then we want to write some data
        // to the socket we have opened a connection to on the given port
        
        if (clientSocket == null || inFromServer == null) {
            System.err.println( "Something is wrong. One variable is null." );
            return;
        }

	}
	
//	public int[] sendValuesToClientUI() {
//		return values_received;
//	}

    public synchronized void stopClient(){
        this.clientstatus=false;
        try{
            // clean up:
            inFromServer.close();  // close the input stream
            clientSocket.close();  // close the socket
            clientSocket.close();

        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

}

