package Client;

import Shared.Constant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import org.jfree.chart.ChartPanel;

/*
* The class ClientUI creates a JFrame with 2 sub-panels:
* 1. dataPanel: contains a graphPanel which plots a graph using incoming values
* User can input number of channels and frequency of incoming data from server
* JLabels, JTextPane display important statistics of the received data
* 2. consolePanel: used to display any error information to the user
* ClientUI also has a Start/Stop button to control the receiving of data from the server
*/


public class ClientUI{

    public Color LIGHTBLUE = Constant.LIGHTBLUE;
    public Color LIGHTPINK = Constant.LIGHTPINK;
    private String[] valuesForDropDown = new String[] {"1", "2", "3", "4", "5"};
    private JComboBox<String> channelDropDown;
    int selectedValue = 1;
    private PlotGraph chart;
    private ChartPanel chartPanel;

    Client client;
    private boolean clientRunning = false;

    /*
    * Constructor ClientUI creates a UI for the Client using a JFrame
    * that contains 2 JPanels. These contain a graph, highest, lowest value,
    * average value of the data received and a console for displaying errors.
    */

    public ClientUI() {

        JFrame mainFrame = new JFrame();

        mainFrame.setTitle("Client");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800,800);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);

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
        mainFrame.add(startStop);

        /* dataPanel is the main Panel with Graph plotting and statistics */
        JPanel dataPanel = new JPanel();
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        dataPanel.setBackground(Color.lightGray);
        dataPanel.setBounds(10, 50, 760, 550);
        mainFrame.add(dataPanel);
        dataPanel.setLayout(null);

        /* This panel is used to display exceptions/errors to the user */
        JPanel consolePanel = new JPanel();
        consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        consolePanel.setBackground(Color.lightGray);
        consolePanel.setBounds(10, 610, 760, 130);
        mainFrame.add(consolePanel);
        consolePanel.setLayout(null);
	    
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
       
        dataPanel.add(channelDropDown);
        dataPanel.add(channelDropDown);
        
        /* sub-panel of dataPanel to display graph */
        JPanel graphPanel = new JPanel();
        //TODO Get numbers from the client 
        int inputValues[] = {5, 2, 6, 3, 8, 3, 9, 1, 10, 7};
	    chart = new PlotGraph("");
	    chartPanel = chart.PlotGraphMethod(selectedValue,inputValues);
        chart.pack( );          
	    	chartPanel.setVisible(false);     
        channelDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedValue = Integer.parseInt(channelDropDown.getSelectedItem().toString());
                chart = new PlotGraph("");
                chartPanel = chart.PlotGraphMethod(selectedValue,inputValues);
                chart.pack( );          
        	    	    chartPanel.setVisible( true ); 
        	    	    graphPanel.add(chartPanel);
        	    	    graphPanel.setVisible(true);
        	    	    dataPanel.add(graphPanel);
        	    	    dataPanel.setVisible(true);
        	    	    mainFrame.add(dataPanel);
        	    	    dataPanel.repaint();
        	    	    mainFrame.setVisible(true);
        	    	    mainFrame.repaint();
            }
        });
     
	    graphPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        graphPanel.setBackground(LIGHTPINK);
        graphPanel.setBounds(15,15,520,520);
        graphPanel.add(chartPanel);
        dataPanel.add(graphPanel);
        graphPanel.setLayout(null);

        /* Highest Value Label */
        JLabel highValueLabel = new JLabel("<html>Highest<br>value:</html>");
        highValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        highValueLabel.setBackground(LIGHTBLUE);
        highValueLabel.setBounds(560,15,85,60);
        highValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highValueLabel.setOpaque(true);
        dataPanel.add(highValueLabel);

        /* Highest Value calculation */
        int maxValue = -999;
        for (int i = 1; i < inputValues.length; i++)
        {
            if (inputValues[i] > maxValue)
            {
                maxValue = inputValues[i];
            }
        }

        /* Highest Value Number Label */
        JLabel highestValue = new JLabel(""+maxValue+"",SwingConstants.CENTER);
        highestValue.setBorder(BorderFactory.createLineBorder(Color.black));
        highestValue.setBackground(LIGHTPINK);
        highestValue.setOpaque(true);
        highestValue.setBounds(655,15,85,60);
        dataPanel.add(highestValue);

        /* Lowest Value Label */
        JLabel lowValueLabel = new JLabel("<html>Lowest<br>value:</html>");
        lowValueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        lowValueLabel.setBackground(LIGHTPINK);
        lowValueLabel.setBounds(560,90,85,60);
        lowValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lowValueLabel.setOpaque(true);
        dataPanel.add(lowValueLabel);

        /* Lowest Value Calculation */
        int minValue = 999;
        for (int i = 1; i < inputValues.length; i++)
        {
            if (inputValues[i] < minValue)
            {
                minValue = inputValues[i];
            }
        }

        /* Lowest Value Number Label */
        JLabel lowestValue = new JLabel(""+minValue+"",SwingConstants.CENTER);
        lowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
        lowestValue.setBackground(LIGHTBLUE);
        lowestValue.setOpaque(true);
        lowestValue.setBounds(655,90,85,60);
        dataPanel.add(lowestValue);

        /* Average Value Label */
        JLabel avgLabel = new JLabel("<html>Average</html>");
        avgLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        avgLabel.setBackground(LIGHTBLUE);
        avgLabel.setBounds(560,165,85,60);
        avgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avgLabel.setOpaque(true);
        dataPanel.add(avgLabel);

        /* Average Value calculation */
        int sum = 0;
        int arraySize = inputValues.length;
        for (int i = 1; i < arraySize; i++)
        {
            sum += i;
        }
        int avgValue = sum/arraySize;

        /* Average Value Number Label */
        JLabel avgValueDisplay = new JLabel(""+avgValue+"",SwingConstants.CENTER);
        avgValueDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
        avgValueDisplay.setBackground(LIGHTPINK);
        avgLabel.setOpaque(true);
        avgValueDisplay.setBounds(655,165,85,60);
        dataPanel.add(avgValueDisplay);

        /* Frequency Label */
        JLabel frequencyLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
        frequencyLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        frequencyLabel.setBackground(LIGHTBLUE);
        frequencyLabel.setBounds(560,325,85,60);
        frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frequencyLabel.setOpaque(true);
        dataPanel.add(frequencyLabel);

        /* Frequency Text Field */
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class);
        numberFormatter.setAllowsInvalid(false);
        JFormattedTextField frequencyValue = new JFormattedTextField(numberFormatter);
        frequencyValue.setBorder(BorderFactory.createLineBorder(Color.black));
        frequencyValue.setBackground(LIGHTPINK);
        frequencyValue.setBounds(655,325,85,60);
        dataPanel.add(frequencyValue);
        
        dataPanel.repaint();
        consolePanel.repaint();
        
        mainFrame.setVisible(true);
       
    }
    
    public static void main(String[] args) //main
    {
        ClientUI client = new ClientUI();
    }
}

