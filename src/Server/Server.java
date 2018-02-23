package Server;

import Shared.Constant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
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
    private BufferedWriter bufferWriter = null;
    private ServerSocket listener=null;
    private int highest_value =1024;
    private int lowest_value =0;
    private int no_of_channels=2 ;
    private int frequency=2 ;
    private int port = Constant.PORT_NUMBER;
    Socket socket;

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
        ServerStatus = true;
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

	/* server starts and sends random numbers to the client 
	 * */
    public void StartServer () { 

            
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
                	System.out.println("here");

                   this.ServerStatus = false;    
                	try {
                     socket = listener.accept();
                	}
                	catch(Exception e) {
                		e.printStackTrace();
                	}
                    System.out.println("2");
                    System.out.println(socket+" ===");

                    try {

                        
                        outToClient = new DataOutputStream(socket.getOutputStream());
                        
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bufferWriter = new BufferedWriter(osw);
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                            	try {
									bufferWriter.write("1,2,3,4");
									bufferWriter.flush();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                            }});
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
