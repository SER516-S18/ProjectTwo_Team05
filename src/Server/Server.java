/**
 * @SER516 Project2_Team05
 */

package Server;

import Shared.Constant;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Server class first accepts number of channels and frequency from the client
 * and then generates and sends random numbers to the client class
 * according to the number of channels and frequency passed using the socket
 */

public class Server implements Runnable {
	private boolean ServerStatus;
    private DataInputStream input_stream =null;
    private DataOutputStream outToClient=null;
    private BufferedWriter bufferWriter = null;
    private ServerSocket listener=null;
    private int highest_value = 1024;
    private int lowest_value = 0;
    private int frequency = 2;
    private int port = Constant.PORT_NUMBER;
    private String string_stream = "";
    Socket socket;

	/**
	 * The constructor will set the ServerStatus to false initially
	 **/
	public Server() {
		ServerStatus = true;
	}

	@Override
	public void run() {
		this.StartServer();
	}
	
	/**
	 * setValues method will get the values from the text box of highestValue, lowestValue
	 * and frequency
	 * @param highestValue
	 * @param lowestValue
	 * @param frequency
	 **/
	public void setValues(String highestValue, String lowestValue, String frequency) {
		if (highestValue != null) {
			this.highest_value = Integer.parseInt(highestValue);
		}
		if (lowestValue != null) {
			this.lowest_value = Integer.parseInt(lowestValue);
		}
		if (frequency != null) {
			this.frequency = Integer.parseInt(frequency);
		}
	}

	/**
	 * createThreadForServer create a new server instance for the thread
	 * and start the thread with new server
	 * return the thread
	 * @return server
	 **/
	public static Server createThreadForServer() {
		Server server = new Server();
		new Thread(server).start();
		return server;
	}

	/**
	 * checkServerStatus will return the current Server Status
	 * @return this.ServerStatus
	 **/
	private boolean checkServerStatus() {
		return this.ServerStatus;
	}

	/**
	 * StopServer method will stop the server on button click and close the socket and 
	 * listener
	 **/
	public synchronized void StopServer() {
		this.ServerStatus = false;
		try {
			socket.close();
			this.listener.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Server starts and sends random numbers to the client
	 */
	public void StartServer() {
		Random randomNumber = new Random();
        try{

            listener = new ServerSocket(port);
        }
        catch(Exception e)

        {
            System.out.print(e.getMessage());
        }

        try {
            socket = listener.accept();
       	}
       	catch(Exception e) {
       		e.printStackTrace();
       	}
        
        while(this.checkServerStatus()) {

            try {
            		this.ServerStatus = false;
                try {
                    outToClient = new DataOutputStream(socket.getOutputStream());
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    bufferWriter = new BufferedWriter(osw);
                    Thread thread = new Thread(new Runnable() {
                        public void run() {
                        	try {
                            		while(true) {
                            			for (int j = 0; j < frequency; j++) {
                               			 Integer valuesToBeSend = lowest_value 
                               					 + randomNumber.nextInt(highest_value);
                               			 string_stream += valuesToBeSend.toString() + ",";
                                     }
   	                        		 	string_stream += "\n";
   									bufferWriter.write(string_stream);	
   									bufferWriter.flush();
                            		}
							} catch (IOException e) {
								e.printStackTrace();
							}
                        }});
                    thread.start();
   

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
