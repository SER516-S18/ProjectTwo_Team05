//package Project2_team05;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main (String args[]) throws IOException
    {
        ServerSocket listener = new ServerSocket(1516);
        int highest_value = 1024;
        int lowest_value = 0;
        int stream[]=new int[100]; 
    	int no_of_channels=2;
    	int frequency=2;
        Random randomNumber = new Random();
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                	DataInputStream din=new DataInputStream(socket.getInputStream());  
                	DataOutputStream outToClient=new DataOutputStream(socket.getOutputStream());  
                	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
                	  
                    Thread thread = new Thread(new Runnable() {
                    	public void run() {
                		 while(true)
                		 {
		                	for(int j=1;j<=frequency;j++)
		                	{
		                		for(int i=1;i<=no_of_channels&&stream[i]>lowest_value;i++)
		                		{
		                			stream[i]=randomNumber.nextInt(highest_value);
		                		}
		                			for(int i=1;i<=no_of_channels;i++) 
		                		{
		                			try {
										outToClient.writeInt(stream[i]);
									} catch (IOException e) {
										e.printStackTrace();
									}  
		                		}
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
                 
                    socket.close();               
            
		        }
                catch (Exception e) {
					e.printStackTrace();
				}
		        finally {
		            listener.close();
		        } 
            }
        
        } catch (Exception e) {}
    }
}
