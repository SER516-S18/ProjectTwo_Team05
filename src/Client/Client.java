package Client;

import Shared.Constant;
import java.io.BufferedReader;
import java.io.DataOutputStream;
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
       try {
       	 	InetAddress address = InetAddress.getByName(hostname);
           clientSocket = new Socket(address, port);
   			
           InputStream is = clientSocket.getInputStream();
           InputStreamReader isr = new InputStreamReader(is);
           inFromServer = new BufferedReader(isr);
           
           while ((data = inFromServer.readLine()) != null) {
               String[] stringArray = data.split(",");
               
               for (int i = 0; i < stringArray.length; i++) {
                  Integer numberReceived = Integer.parseInt(stringArray[i]);
                  values_received.add(numberReceived);
               }
	       if(stringArray.length<frequency){
            	   int buffer_value=frequency-stringArray.length;                   
                   for(int k=0;k<buffer_value;k++)
			   {  
				   values_received.add(0);                   
			   }            	   
               	}
               else{
            	   values_received.add(numberReceived);            	   
               }
           }
       } catch (UnknownHostException e) {
           ClientConsole.getClientConsole().display("Don't know about host: " + hostname);
       } catch (IOException e) {
           ClientConsole.getClientConsole().display("Couldn't get I/O for the connection "
           		+ "to: " + hostname);
           ClientConsole.getClientConsole().display(e.getMessage());
       }
       
       if (clientSocket == null || inFromServer == null) {
           ClientConsole.getClientConsole().display("Something is wrong. One variable is null.");
           return;
       }
	}

    public synchronized void stopClient(){
        this.clientstatus=false;
        try{
            inFromServer.close();
            clientSocket.close();
        } catch(IOException e) {
            ClientConsole.getClientConsole().display(e.getMessage());
        }

    }
    
   /* public int[] sendValuesToClientUI() {
		int inputValues[] = {1, 2, 3,4 , 5, 5, 6, 7,8, 9, 10};
		return inputValues;
	}*/
	

	public ArrayList<Integer> sendValuesToClientUI() {
		//int inputValues[] = {1, 2, 3,4 , 5, 5, 6, 7,8, 9, 10}
		return values_received;
	}

}

