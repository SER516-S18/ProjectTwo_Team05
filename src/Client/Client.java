package Client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 1516;

        // declaration section:
        // clientSocket: our client socket
        // os: output stream
        // is: input stream

        Socket clientSocket = null;
        DataOutputStream os = null;
        BufferedReader is = null;

        // Initialization section:
        // Try to open a socket on the given port
        // Try to open input and output streams

        try {
            clientSocket = new Socket(hostname, port);
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String responseLine = null;
            while ( ( responseLine =is.readLine())!=null) {

                System.out.println( responseLine);

            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
            System.err.println(e.getStackTrace());
        }

        // If everything has been initialized then we want to write some data
        // to the socket we have opened a connection to on the given port

        if (clientSocket == null || os == null || is == null) {
            System.err.println( "Something is wrong. One variable is null." );
            return;
        }

        try {

            // clean up:
            os.close(); // close the output stream
            is.close();  // close the input stream
            clientSocket.close();  // close the socket

        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}