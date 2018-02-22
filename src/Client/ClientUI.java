package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.NumberFormatter;


import java.text.NumberFormat;

/*
* @author  Rachana Kashyap
* @version 1.0
* @since   02-18-2018
* The class ClientUI creates a JFrame with 2 sub-panels:
* 1. dataPanel: contains a graphPanel which plots a graph using incoming values
* User can input number of channels and frequency of incoming data from server
* JLabels, JTextPane display important statistics of the received data
* 2. consolePanel: used to display any error information to the user
* ClientUI also has a Start/Stop button to control the receiving of data from the server
*/


public class ClientUI {
	
    public Color LIGHTBLUE = new Color(173,216,230);
    public Color LIGHTPINK = new Color(255,182,193);
    private String[] valuesForDropDown = new String[] {"1", "2", "3", "4", "5"};
    private JComboBox<String> channelDropDown;

    Client client;
    private boolean clientRunning = false;
    
    public ClientUI() {
    	
        JFrame f = new JFrame();

        f.setTitle("Client");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,800);
        f.setVisible(true);
        f.setLayout(null);

        /* Button to start/stop receiving data from the server */
        JButton startStop = new JButton("start / stop");
        startStop.setBackground(LIGHTPINK);
        startStop.setBounds(580, 10, 190, 30);
        startStop.setBorder(BorderFactory.createLineBorder(Color.black));
        startStop.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            		clientRunning = !clientRunning;
            		client = client.getClientInstance();
                if(clientRunning)
                {
                    System.out.println("Client is running..."); // print command on console
                    client.startClient();
                }
                else
                {
                    System.out.println("Client is Stopped...");  // print command on console
                    client.stopClient();
                }
            }
        });
        f.add(startStop);

        /* dataPanel is the main Panel with Graph plotting and statistics */
        JPanel dataPanel = new JPanel();
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        dataPanel.setBackground(Color.lightGray);
        dataPanel.setBounds(10, 50, 760, 550);
        f.add(dataPanel);
        dataPanel.setLayout(null);

        /* This panel is used to display exceptions/errors to the user */
        JPanel consolePanel = new JPanel();
        consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        consolePanel.setBackground(Color.lightGray);
        consolePanel.setBounds(10, 610, 760, 130);
        f.add(consolePanel);
        consolePanel.setLayout(null);

        /* sub-panel of dataPanel to display graph */
        JPanel graphPanel = new JPanel();
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        graphPanel.setBackground(LIGHTPINK);
        graphPanel.setBounds(15,15,520,520);
        dataPanel.add(graphPanel);
        graphPanel.setLayout(null);

        /* Highest Value Label */
        JLabel highVal = new JLabel("<html>Highest<br>value:</html>");
        highVal.setBorder(BorderFactory.createLineBorder(Color.black));
        highVal.setBackground(LIGHTBLUE);
        highVal.setBounds(560,15,85,60);
        highVal.setHorizontalAlignment(SwingConstants.CENTER);
        highVal.setOpaque(true);
        dataPanel.add(highVal);

        /* Highest Value TextPane */
        JTextPane highTxt = new JTextPane();
        highTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        highTxt.setBackground(LIGHTPINK);
        highTxt.setBounds(655,15,85,60);
        dataPanel.add(highTxt);

        /* Lowest Value Label */
        JLabel lowVal = new JLabel("<html>Lowest<br>value:</html>");
        lowVal.setBorder(BorderFactory.createLineBorder(Color.black));
        lowVal.setBackground(LIGHTPINK);
        lowVal.setBounds(560,90,85,60);
        lowVal.setHorizontalAlignment(SwingConstants.CENTER);
        lowVal.setOpaque(true);
        dataPanel.add(lowVal);

        /* Lowest Value TextPane */
        JTextPane lowTxt = new JTextPane();
        lowTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        lowTxt.setBackground(LIGHTBLUE);
        lowTxt.setBounds(655,90,85,60);
        dataPanel.add(lowTxt);

        /* Average Value Label */
        JLabel average = new JLabel("<html>Average</html>");
        average.setBorder(BorderFactory.createLineBorder(Color.black));
        average.setBackground(LIGHTBLUE);
        average.setBounds(560,165,85,60);
        average.setHorizontalAlignment(SwingConstants.CENTER);
        average.setOpaque(true);
        dataPanel.add(average);

        /* Average Value TextPane */
        JTextPane avgTxt = new JTextPane();
        avgTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        avgTxt.setBackground(LIGHTPINK);
        avgTxt.setBounds(655,165,85,60);
        dataPanel.add(avgTxt);

        /* Channels Label */
        JLabel channels = new JLabel("<html>Channels:</html>");
        channels.setBorder(BorderFactory.createLineBorder(Color.black));
        channels.setBackground(LIGHTPINK);
        channels.setBounds(560,245,85,60);
        channels.setHorizontalAlignment(SwingConstants.CENTER);
        channels.setOpaque(true);
        dataPanel.add(channels);

        /*Channels Dropdown and select Combobox*/
        channelDropDown = new JComboBox<String>(valuesForDropDown);
        channelDropDown.setVisible(true);
        channelDropDown.setBorder(BorderFactory.createLineBorder(Color.black));
        channelDropDown.setBackground(LIGHTBLUE);
        channelDropDown.setBounds(655,245,85,60);
        channelDropDown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = (String)channelDropDown.getSelectedItem();

            }
        });
        dataPanel.add(channelDropDown);
        dataPanel.add(channelDropDown);

        /* Frequency Label */
        JLabel frequency = new JLabel("<html>Frequency<br>(Hz):</html>");
        frequency.setBorder(BorderFactory.createLineBorder(Color.black));
        frequency.setBackground(LIGHTBLUE);
        frequency.setBounds(560,325,85,60);
        frequency.setHorizontalAlignment(SwingConstants.CENTER);
        frequency.setOpaque(true);
        dataPanel.add(frequency);

        /* Frequency Text Field */
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class);
        numberFormatter.setAllowsInvalid(false);
        JFormattedTextField freqTxt = new JFormattedTextField(numberFormatter);
        freqTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        freqTxt.setBackground(LIGHTPINK);
        freqTxt.setBounds(655,325,85,60);
        dataPanel.add(freqTxt);


        dataPanel.repaint();
        consolePanel.repaint();
    }
    
    public static void main(String[] args) //main
    {
        ClientUI client = new ClientUI();
    }
}

