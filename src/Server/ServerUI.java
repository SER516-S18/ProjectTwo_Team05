package Server;

import Shared.Constant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * The below class creates a Window Server.
 * @author (Venkata Akhil Madaraju)
 * @version (2/16/18)
 */

public class ServerUI
{
    public static Color LIGHTBLUE = Constant.LIGHTBLUE;
    public static Color LIGHTPINK = Constant.LIGHTPINK;
    Server serverThread;
    public static boolean ServerRunning = false;
    private ServerConsole console ;
    private final Color COLOR_OFF = Color.red;
    private final Color COLOR_ON_BRIGHT = new Color(168,208,141);

    public ServerUI()
    {
        JFrame f = new JFrame();
        console = new ServerConsole();
        f.setTitle("Server");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(800,800);
        f.setBounds(100,100 ,800 ,800 );
        f.setVisible(true);
        f.setLayout(null);

        JPanel consolePanel = console.getConsolePanel();
        f.add(consolePanel);
        consolePanel.setLayout(null);


        JButton startStop = new JButton("start / stop");
        startStop.setBackground(LIGHTPINK);
        startStop.setBounds(580, 15, 190, 30);
        startStop.setBorder(BorderFactory.createLineBorder(Color.black));
       
        f.add(startStop);


        JPanel upPanel = new JPanel();
        upPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        upPanel.setBackground(Color.lightGray);
        upPanel.setBounds(10, 50, 760, 550);
        f.add(upPanel);
        upPanel.setLayout(null);


        /*Highest Value Label */
        JLabel highestText = new JLabel("<html>Highest<br>value:</html>");
        highestText.setBorder(BorderFactory.createLineBorder(Color.black));
        highestText.setBackground(LIGHTBLUE);
        highestText.setBounds(560,15,85,60);
        highestText.setHorizontalAlignment(SwingConstants.CENTER);
        highestText.setOpaque(true);
        upPanel.add(highestText);

        /* Highest Value TextPane */
        JTextPane highestValue = new JTextPane();
        highestValue.setBorder(BorderFactory.createLineBorder(Color.black));
        highestValue.setBackground(LIGHTPINK);
        highestValue.setBounds(655,15,85,60);
        upPanel.add(highestValue);

        /* Lowest Value Label */
        JLabel lowestText = new JLabel("<html>Lowest<br>value:</html>");
        lowestText.setBorder(BorderFactory.createLineBorder(Color.black));
        lowestText.setBackground(LIGHTPINK);
        lowestText.setBounds(560,90,85,60);
        lowestText.setHorizontalAlignment(SwingConstants.CENTER);
        lowestText.setOpaque(true);
        upPanel.add(lowestText);

        /* Lowest Value TextPane */
        JTextPane lowestValue = new JTextPane();
        lowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
        lowestValue.setBackground(LIGHTBLUE);
        lowestValue.setBounds(655,90,85,60);
        upPanel.add(lowestValue);

        JLabel frequency = new JLabel("<html>Frequency<br>(Hz):</html>");
        frequency.setBorder(BorderFactory.createLineBorder(Color.black));
        frequency.setBackground(LIGHTBLUE);
        frequency.setBounds(560,165,85,60);
        frequency.setHorizontalAlignment(SwingConstants.CENTER);
        frequency.setOpaque(true);
        upPanel.add(frequency);

        JTextPane frequencyValue = new JTextPane();
        frequencyValue.setBorder(BorderFactory.createLineBorder(Color.black));
        frequencyValue.setBackground(LIGHTPINK);
        frequencyValue.setBounds(655,165,85,60);
        upPanel.add(frequencyValue);

        ServerStatusView statusView = new ServerStatusView();

        statusView.setBounds(15, 15, 520, 520);
        upPanel.add(statusView);

        startStop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ServerRunning = !ServerRunning;

                if(ServerRunning)
                {

                    if(!(highestValue.getText()).equals("") && !(lowestValue.getText()).equals("") && !(frequencyValue.getText()).equals(""))
                    {
                        console.setConsoleMessage("Server is Running on Port Number 1516.");
                        setThreadForServer(Server.createThreadForServer());
                        serverThread.setValues(highestValue.getText(),lowestValue.getText() ,frequencyValue.getText());
                        statusView.running = true;
                        statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
                    }
                    else if ((highestValue.getText()).equals("") && (lowestValue.getText()).equals("") && (frequencyValue.getText()).equals(""))
                    {
                        console.setConsoleMessage("Default Highest value , Lowest value and Frequency will be used. Server is Running on Port Number 1516.");
                        setThreadForServer(Server.createThreadForServer());
                        statusView.running = true;
                        statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
                    }
                    else if ((highestValue.getText()).equals(""))
                    {
                        console.setConsoleMessage("HighestValue is not entered. Default Value 1024 will be used as highest value. Server is Running on Port Number 1516.");
                        setThreadForServer(Server.createThreadForServer());
                        statusView.running = true;
                        statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
                    }
                    else if ((lowestValue.getText()).equals(""))
                    {
                        console.setConsoleMessage("LowestValue is not entered. Default Value 0 will be used as Lowest value. Server is Running on Port Number 1516.");
                        setThreadForServer(Server.createThreadForServer());
                        statusView.running = true;
                        statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
                    }
                    else if ((frequencyValue.getText()).equals("") )
                    {
                        console.setConsoleMessage("Frequency is not entered. Default Value 2 will be used for Frequency.Server is Running on Port Number 1516.");
                        setThreadForServer(Server.createThreadForServer());
                        statusView.running = true;
                        statusView.statusBlinker.setForeground(COLOR_ON_BRIGHT);
                    }

                }
                else
                {
                    statusView.running = false;
                    statusView.statusBlinker.setForeground(COLOR_OFF);
                    //console.setConsole(consoleMessage);
                    console.setConsoleMessage("Server is Stopped on Port Number 1516.");
                    serverThread = getThreadForServer();
                    serverThread.StopServer();
                }
            }
        });
        upPanel.repaint();
        consolePanel.repaint();
    }

    public void setThreadForServer(Server serverThread)
    {
        this.serverThread = serverThread;
    }

    public Server getThreadForServer()
    {
        return this.serverThread;
    }

    public static void main(String[] args) //main
    {
        ServerUI s = new ServerUI();
    }
}
