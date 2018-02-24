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
    private BufferedWriter bufferWriter = null;
    private ServerSocket listener=null;
    private int highest_value = 1024;
    private int lowest_value = 0;
    private int frequency = 2;
    private int port = Constant.PORT_NUMBER;
    private String string_stream = "";

    Socket socket;

    public Server()
    {
        ServerStatus = false;
    }
    @Override
    public void run(){
        this.StartServer();
    }

    public void setValues(String highestValue , String lowestValue , String frequency)
    {
        if(highestValue!=null)
            this.highest_value = Integer.parseInt(highestValue);
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



    public synchronized void StopServer(){
        this.ServerStatus=false;
        System.out.println(this.ServerStatus);
        try{
            socket.close();
            this.listener.close();
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

	/* server starts and sends random numbers to the client 
	 * */
    public void StartServer () { 

            this.ServerStatus =true;
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
            
            while(checkServerStatus()) {
                try {
                  // this.ServerStatus = false;
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
	                               			 Integer valuesToBeSend = lowest_value + randomNumber.nextInt(highest_value);
	                               			 string_stream += valuesToBeSend.toString() + ",";
	                                     }
	   	                        		 	string_stream += "\n";
	   									bufferWriter.write(string_stream);	
	   									bufferWriter.flush();
	                            		}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                            }});
                        thread.start();
       
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
//                        socket.close();
//                        listener.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    // print error message on console
                }
            }
    }
}
