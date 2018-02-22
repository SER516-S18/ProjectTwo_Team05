package Server;

import Shared.Constant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;// imports all the functions present in the swing library
import java.awt.*;// imports all the functions present in awt library
/**
 * The below class creates a Window Server.
 * @author (Venkata Akhil Madaraju)
 * @version (2/16/18)
 */

public class ServerUI
{
    public Color LIGHTBLUE = Constant.LIGHTBLUE;
    public Color LIGHTPINK = Constant.LIGHTPINK;
    Server serverThread;
    private boolean ServerRunning = false;

    public ServerUI()
    {
        JFrame f = new JFrame();

        f.setTitle("Server");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(800,800);
        f.setBounds(100,100 ,800 ,800 );
        f.setVisible(true);
        f.setLayout(null);


        JButton startStop = new JButton("start / stop");
        startStop.setBackground(LIGHTPINK);
        startStop.setBounds(580, 15, 190, 30);
        startStop.setBorder(BorderFactory.createLineBorder(Color.black));

        startStop.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                ServerRunning = !ServerRunning;

                if(ServerRunning)
                {
                    System.out.println("Server is running..."); // print command on console
                    setThreadForServer(Server.createThreadForServer());
                }
                else
                {
                    serverThread = getThreadForServer();
                    serverThread.StopServer();
                    System.out.println("Server is Stopped...");  // print command on console
                }
            }
        });
        f.add(startStop);



        JPanel upPanel = new JPanel();
        upPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        upPanel.setBackground(Color.lightGray);
        upPanel.setBounds(10, 50, 760, 550);
        f.add(upPanel);
        upPanel.setLayout(null);


        JPanel downPanel = new JPanel();
        downPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        downPanel.setBackground(Color.lightGray);
        downPanel.setBounds(10, 610, 760, 130);
        f.add(downPanel);
        downPanel.setLayout(null);
        
        JLabel consoleLabel = new JLabel(" Console :");
        consoleLabel.setBounds(5, 10, 80, 10);
        downPanel.add(consoleLabel);
        


        JPanel circle = new Circle(235, 225, 130, Color.green);
        circle.setBounds(15, 15, 520, 520);
        circle.setBackground(LIGHTPINK);
        circle.setBorder(BorderFactory.createLineBorder(Color.black));
        upPanel.add(circle);


        /*Highest Value Label */
        JLabel highVal = new JLabel("<html>Highest<br>value:</html>");
        highVal.setBorder(BorderFactory.createLineBorder(Color.black));
        highVal.setBackground(LIGHTBLUE);
        highVal.setBounds(560,15,85,60);
        highVal.setHorizontalAlignment(SwingConstants.CENTER);
        highVal.setOpaque(true);
        upPanel.add(highVal);

        /* Highest Value TextPane */
        JTextPane highTxt = new JTextPane();
        highTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        highTxt.setBackground(LIGHTPINK);
        highTxt.setBounds(655,15,85,60);
        upPanel.add(highTxt);

        /* Lowest Value Label */
        JLabel lowVal = new JLabel("<html>Lowest<br>value:</html>");
        lowVal.setBorder(BorderFactory.createLineBorder(Color.black));
        lowVal.setBackground(LIGHTPINK);
        lowVal.setBounds(560,90,85,60);
        lowVal.setHorizontalAlignment(SwingConstants.CENTER);
        lowVal.setOpaque(true);
        upPanel.add(lowVal);

        /* Lowest Value TextPane */
        JTextPane lowTxt = new JTextPane();
        lowTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        lowTxt.setBackground(LIGHTBLUE);
        lowTxt.setBounds(655,90,85,60);
        upPanel.add(lowTxt);

        JLabel frequency = new JLabel("<html>Frequency<br>(Hz):</html>");
        frequency.setBorder(BorderFactory.createLineBorder(Color.black));
        frequency.setBackground(LIGHTBLUE);
        frequency.setBounds(560,165,85,60);
        frequency.setHorizontalAlignment(SwingConstants.CENTER);
        frequency.setOpaque(true);
        upPanel.add(frequency);

        JTextPane freqTxt = new JTextPane();
        freqTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        freqTxt.setBackground(LIGHTPINK);
        freqTxt.setBounds(655,165,85,60);
        upPanel.add(freqTxt);


        upPanel.repaint();
        downPanel.repaint();
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
