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

public class Server  implements Runnable{
// comment test
    private boolean ServerStatus;
    private DataInputStream input_stream =null;
    private DataOutputStream outToClient=null;
    private BufferedReader buffer=null;
    private ServerSocket listener=null;
    private int highest_value = 1024;
    private int lowest_value = 0;
    private int no_of_channels = 2;
    private int frequency =2;
    private int port = Constant.PORT_NUMBER;

    @Override
    public void run(){
        System.out.println("Running Server");
        this.StartServer();
    }


    public static Server createThreadForServer()
    {
        Server server = new Server();
        System.out.println("creating new thread");
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

        System.out.println("Server has stopped");
        // print message on console
    }
    
	public int getFrequency() {
		try {
			frequency= buffer.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return frequency;
	}
	
	public int getChannels() {
		try {
			no_of_channels= buffer.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return no_of_channels;
	}
	
    public void StartServer () {            
            int stream[] = new int[100];
            
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
                                while (!checkServerStatus()) {

                                    for (int j = 1; j <= frequency; j++) {
                                        for (int i = 1; i <= no_of_channels; i++) {
                                            stream[i] = lowest_value + randomNumber.nextInt(highest_value);
                                        }
                                        for (int i = 1; i <= no_of_channels; i++) {
                                            System.out.print(stream[i]);
											try {
												outToClient.writeInt(stream[i]);
											} catch (IOException e) {
												// TODO Auto-generated catch block
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
