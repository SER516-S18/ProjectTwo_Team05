package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class server {

    static server serverInstance = new server();
    private boolean ServerStatus ;
    DataInputStream input_stream =null;
    PrintStream outToClient=null;
    BufferedReader buffer=null;
    ServerSocket listener=null;
    public static server getServerInstance()
    {
        return serverInstance ;
    }

    private boolean checkServerStatus()
    {
        return this.ServerStatus;
    }

    public server()
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

    public void StartServer () {

            int highest_value = 1024;
            int lowest_value = 0;
            int stream[] = new int[100];
            int no_of_channels = 2;
            int frequency = 2;
            Random randomNumber = new Random();
            try{
                listener = new ServerSocket(1516);
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
                        outToClient = new PrintStream(socket.getOutputStream());
                        buffer = new BufferedReader(new InputStreamReader(System.in));


                        while(true){
                            Integer num = lowest_value + randomNumber.nextInt(highest_value);
                            outToClient.println(num);

                        }
//                        Thread thread = new Thread(new Runnable() {
//                            public void run() {
//                                while (true) {

//                                    for (int j = 1; j <= frequency; j++) {
//                                        for (int i = 1; i <= no_of_channels; i++) {
//                                            stream[i] = lowest_value + randomNumber.nextInt(highest_value);
//                                        }
//                                        for (int i = 1; i <= no_of_channels; i++) {
//                                            try {
//                                                System.out.print(stream[i]);
//                                                outToClient.writeInt(stream[i]);
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }


//                                    try {
//                                        Thread.sleep(1000);
//                                    } catch (InterruptedException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        });
//                        thread.start();



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
