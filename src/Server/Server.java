package Server;

import Shared.Constant;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
/*
 * Server class first accepts number of channels and frequency from
 * the client class and then generates and sends random numbers to the
 * client class according to the number of channels and frequency passed
 * using the socket  
 */

public class Server  implements Runnable{
    private boolean ServerStatus;
    private DataInputStream input_stream =null;
    private DataOutputStream outToClient=null;
    private BufferedReader buffer=null;
    private ServerSocket listener=null;
    private int highest_value =1024;
    private int lowest_value =0;
    private int no_of_channels=2 ;
    private int frequency=2 ;
    private int port = Constant.PORT_NUMBER;

    @Override
    public void run(){
        this.StartServer();
    }


    public void setValues(String highestValue , String lowestValue , String frequency)
    {
        if(highestValue!=null)
            this.highest_value =Integer.parseInt(highestValue);
        if(lowestValue!=null)
            this.lowest_value = Integer.parseInt(lowestValue);
        if(frequency!=null)
            this.frequency = Integer.parseInt(frequency);

    }
    public static Server createThreadForServer()
    {
        Server server = new Server();
        new Thread(server).start();
        return server;
    }

    private boolean checkServerStatus()
    {
        return this.ServerStatus;
    }

    public Server()
    {
        ServerStatus = false;
    }

    public synchronized void StopServer(){
        this.ServerStatus=false;
        try{
            this.listener.close();
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void getValuesFromClient () {
    	String data= new String();
    	try {
    	 	  data= buffer.readLine();
    	 	} catch (IOException e) {
    	 		// TODO Auto-generated catch block
    	 	  e.printStackTrace();
    	 	}
    	 String []t=data.split(",");
    	 frequency= Integer.parseInt(t[0]);
    	 no_of_channels = Integer.parseInt(t[1]);
    	 	}
    
    public int getFrequency() {
    	getValuesFromClient();
    	 return frequency;
    	 	}
    
	public int getChannels() {
		getValuesFromClient();
 		return no_of_channels;
	}
	public int getCurrentFrequency() {
		return frequency;
	}
	public int getCurrentChannel() {
		return no_of_channels;
	}
	/* server starts and sends random numbers to the client 
	 * */
    public void StartServer () { 

            int stream[][] = new int[100][100];
            String string_stream=new String();
            Random randomNumber = new Random();
            try{

                listener = new ServerSocket(port);
            }
            catch(Exception e)
            {
                System.out.print(e.getMessage());
            }

            while(this.ServerStatus) {

                try {

                    this.ServerStatus = true;                
                    Socket socket = listener.accept();

                    try {

                        input_stream = new DataInputStream(socket.getInputStream());
                        outToClient = new DataOutputStream(socket.getOutputStream());
                        buffer = new BufferedReader(new InputStreamReader(System.in));                        
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                            	getValuesFromClient();
                                while (!checkServerStatus()) {
                                 	if(frequency!=getFrequency()) {
                                 	frequency=getCurrentFrequency();
                                 	}
                                 	if(no_of_channels!=getChannels()) {
                                     	no_of_channels=getCurrentChannel();
                                     	}
                                	System.out.println("Inside second while");
                                    for (int j = 1; j <= frequency; j++) {
                                        for (int i = 1; i <= no_of_channels; i++) {
                                            stream[j][i] = lowest_value + randomNumber.nextInt(highest_value);
                                        }                                                                                                                                                     
                                    }
                                    try {
										outToClient.writeUTF(stream.toString());
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}       
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                        thread.start();
       
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        socket.close();
                        listener.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    // print error message on console
                }
            }
    }
}
