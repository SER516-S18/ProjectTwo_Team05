package Client;

import Shared.Constant;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
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

public class ClientUIMain {

	public Color LIGHTBLUE = Constant.LIGHTBLUE;
	public Color LIGHTPINK = Constant.LIGHTPINK;
	private String[] valuesForDropDown = new String[] { "1", "2", "3", "4", "5" };
	private JComboBox<String> channelDropDown;
	int selectedValue = 1;
	private PlotGraph chart;
	private ChartPanel chartPanel;
	Client clientThread;
	ArrayList<Integer> valuesReceived = new ArrayList<Integer>();
	JFrame mainFrame;
	JPanel graphPanel;
	JPanel dataPanel;
	
	private boolean clientRunning = false;
	JLabel highestValue;
	JLabel lowestValue;
	JLabel averageValue;
	int inputValues[];

    /*
     * Constructor ClientUI creates a UI for the Client using a JFrame
     * that contains 2 JPanels. These contain a graph, highest, lowest value,
     * average value of the data received and a console for displaying errors.
     */
	public ClientUIMain() {

		mainFrame = new JFrame();

		mainFrame.setTitle("Client");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 800);
		mainFrame.setVisible(true);
		mainFrame.setLayout(null);

		/* Button to start/stop receiving data from the server */
		JButton startStop = new JButton("start / stop");
		startStop.setBackground(LIGHTPINK);
		startStop.setBounds(580, 10, 190, 30);
		startStop.setBorder(BorderFactory.createLineBorder(Color.black));
		startStop.addActionListener(new ActionListener() {
            /*
            * When client is stopped, use values received from server to
            * generate graph and calculate statistics
            * */
			public void actionPerformed(ActionEvent e) {
				clientRunning = !clientRunning;

				if (clientRunning) {
					ClientConsole.getClientConsole().display("Client is running ");
					setThreadForClient(Client.createThreadForClient());
				} else {
					ClientConsole.getClientConsole().display("Client is stopped. ");
					clientThread = getThreadForClient();
					clientThread.stopClient();
					System.out.println("Before Call");
					valuesReceived = clientThread.sendValuesToClientUI();
					System.out.println("After call");
    	    				inputValues = new int[valuesReceived.size()];
    	    				for (int i=0; i < inputValues.length; i++)
    	    				{
    	    					inputValues[i] = valuesReceived.get(i).intValue();
    	    				}
    	    				generateGraph(inputValues);
					calculateStats(inputValues);

				}
			}
		});
		mainFrame.add(startStop);

		/* dataPanel is the main Panel with Graph plotting and statistics */
		dataPanel = new JPanel();
		dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		dataPanel.setBackground(Color.lightGray);
		dataPanel.setBounds(10, 50, 760, 550);
		mainFrame.add(dataPanel);
		dataPanel.setLayout(null);

		JTextPane consoleOutput = new JTextPane();
		consoleOutput.setBackground(Color.LIGHT_GRAY);
		consoleOutput.setBounds(10, 610, 760, 130);
		consoleOutput.setBorder(BorderFactory.createLineBorder(Color.black));
		ClientConsole clientConsole = ClientConsole.getClientConsole();
		clientConsole.setConsolePanel(consoleOutput);
		mainFrame.getContentPane().add(consoleOutput);
		mainFrame.add(consoleOutput);
		consoleOutput.setLayout(null);

		/* Channels Label */
        JLabel channels = new JLabel("<html>Channels:</html>");
        channels.setBorder(BorderFactory.createLineBorder(Color.black));
        channels.setBackground(LIGHTPINK);
        channels.setBounds(560,245,85,60);
        channels.setHorizontalAlignment(SwingConstants.CENTER);
        channels.setOpaque(true);
        dataPanel.add(channels);
        
        /*Channels Dropdown and select Combo box*/
        channelDropDown = new JComboBox<String>(valuesForDropDown);
        channelDropDown.setVisible(true);
        channelDropDown.setBorder(BorderFactory.createLineBorder(Color.black));
        channelDropDown.setBackground(LIGHTBLUE);
        channelDropDown.setBounds(655,245,85,60);
        
    		channelDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedValue = Integer.parseInt(channelDropDown.getSelectedItem().toString());
            }});
        dataPanel.add(channelDropDown);
        dataPanel.add(channelDropDown);
        
        /* sub-panel of dataPanel to display graph */
        graphPanel = new JPanel();
        chart = new PlotGraph("");
        chart.pack( );          
        chart.setVisible(false);

	    graphPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        graphPanel.setBackground(LIGHTPINK);
        graphPanel.setBounds(15,15,520,520);
        dataPanel.add(graphPanel);
        graphPanel.setLayout(null);


		/* Highest Value Label */
		JLabel highValLabel = new JLabel("<html>Highest<br>value:</html>");
		highValLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		highValLabel.setBackground(LIGHTBLUE);
		highValLabel.setBounds(560,15,85,60);
		highValLabel.setHorizontalAlignment(SwingConstants.CENTER);
		highValLabel.setOpaque(true);
		dataPanel.add(highValLabel);

		/* Highest Value Number Label after calculation*/
		highestValue = new JLabel();
		highestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		highestValue.setBackground(LIGHTPINK);
		highestValue.setOpaque(true);
		highestValue.setBounds(655,15,85,60);
		dataPanel.add(highestValue);

		/* Lowest Value Label */
		JLabel lowValLabel = new JLabel("<html>Lowest<br>value:</html>");
		lowValLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		lowValLabel.setBackground(LIGHTPINK);
		lowValLabel.setBounds(560,90,85,60);
		lowValLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lowValLabel.setOpaque(true);
		dataPanel.add(lowValLabel);

		/* Lowest Value Number Label after calculation*/
		lowestValue = new JLabel();
		lowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		lowestValue.setBackground(LIGHTBLUE);
		lowestValue.setOpaque(true);
		lowestValue.setBounds(655,90,85,60);
		dataPanel.add(lowestValue);

		/* Average Value Label */
		JLabel avgValLabel = new JLabel("<html>Average</html>");
		avgValLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		avgValLabel.setBackground(LIGHTBLUE);
		avgValLabel.setBounds(560,165,85,60);
		avgValLabel.setHorizontalAlignment(SwingConstants.CENTER);
		avgValLabel.setOpaque(true);
		dataPanel.add(avgValLabel);

		/* Average Value Number Label after calculation */
		averageValue = new JLabel();
		averageValue.setBorder(BorderFactory.createLineBorder(Color.black));
		averageValue.setBackground(LIGHTPINK);
		averageValue.setOpaque(true);
		averageValue.setBounds(655,165,85,60);
		dataPanel.add(averageValue);

		/* Frequency Label */
		JLabel freqLabel = new JLabel("<html>Frequency<br>(Hz):</html>");
		freqLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		freqLabel.setBackground(LIGHTBLUE);
		freqLabel.setBounds(560, 325, 85, 60);
		freqLabel.setHorizontalAlignment(SwingConstants.CENTER);
		freqLabel.setOpaque(true);
		dataPanel.add(freqLabel);

		/* Frequency Text Field */
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);

		JFormattedTextField frequencyValue = new JFormattedTextField(numberFormatter);
		frequencyValue.setBorder(BorderFactory.createLineBorder(Color.black));
		frequencyValue.setBackground(LIGHTPINK);
		frequencyValue.setBounds(655, 325, 85, 60);
		dataPanel.add(frequencyValue);

		dataPanel.repaint();
		consoleOutput.repaint();

		mainFrame.setVisible(true);
	}

    /*
     * Generate graph based on received values and number of channels
     * */
	public void generateGraph(int []inputValues) {
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

    /*
    * Calculate highest, lowest and average of received values
    * */
	public void calculateStats(int []inputValues) {
		/* Highest Value Calculation */
		int maxValue = -999;
		for (int i = 0; i < inputValues.length; i++) {
			if (inputValues[i] > maxValue) {
				maxValue = inputValues[i];
			}
		}
		/* Lowest Value Calculation */
		int minValue = 999;
		for (int i = 0; i < inputValues.length; i++) {
			if (inputValues[i] < minValue) {
				minValue = inputValues[i];
			}
		}
		/* Average Value calculation */
		int sum = 0;
		int avgValue = 0;
		int arraySize = inputValues.length;
		if(arraySize != 0){
			for (int i = 0; i < arraySize; i++) {
				sum += inputValues[i];
			}
			avgValue = sum/arraySize;
		}
		highestValue.setText(""+maxValue+"");
		lowestValue.setText(""+minValue+"");
		averageValue.setText(""+avgValue+"");
		highestValue.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		averageValue.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void setThreadForClient(Client clientThread) {
		this.clientThread = clientThread;
	}

	public Client getThreadForClient() {
		return this.clientThread;
	}

	public static void main(String[] args) // main
	{
		ClientUIMain client = new ClientUIMain();
	}
}
