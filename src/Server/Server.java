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

public class Server  {
// comment test
    static Server ServerInstance = new Server();
    private boolean ServerStatus;
    DataInputStream input_stream =null;
    DataOutputStream outToClient=null;
    BufferedReader buffer=null;
    ServerSocket listener=null;
    int highest_value = 1024;
    int lowest_value = 0;
    int no_of_channels = 2;
    int frequency =2;
    int port = Constant.PORT_NUMBER;
    
    public static Server getServerInstance()
    {
        return ServerInstance ;
    }

    private boolean checkServerStatus()
    {
        return this.ServerStatus;
    }

    public Server()
    {
        ServerStatus = false;
    }

    public void StopServer(){
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

            while(!checkServerStatus()) {
                try {

                    this.ServerStatus = true;
                    Socket socket = listener.accept();
                    try {

                        input_stream = new DataInputStream(socket.getInputStream());
                        outToClient = new DataOutputStream(socket.getOutputStream());
                        buffer = new BufferedReader(new InputStreamReader(System.in));

                        /*int num=0;
                        while(true)
                           num = lowest_value + randomNumber.nextInt(highest_value);
                            
						*/
                    //   frequency=getFrequency();
                     //   no_of_channels=getChannels();
                        
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
