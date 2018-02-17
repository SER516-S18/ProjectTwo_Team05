package Project2_team05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = JOptionPane.showInputDialog(
                        "running the date service on port 9090:");
        Socket s = new Socket(serverAddress, 1516);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        System.exit(0);
    }
}
