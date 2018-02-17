package Project2_team05;

import java.io.IOException;
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
        Random randomNumber = new Random();
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    int number = lowest_value + randomNumber.nextInt(highest_value);
                    out.println(number);
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }
}
