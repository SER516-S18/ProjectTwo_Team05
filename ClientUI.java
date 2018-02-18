import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame{
    public Color LIGHTBLUE = new Color(173,216,230);
    public Color LIGHTPINK = new Color(255,182,193);
    private String[] valuesForDropDown = new String[] {"1", "2", "3", "4", "5"};
    private JComboBox<String> channelDropDown;

    public ClientUI(){
        JFrame f = new JFrame();

        f.setTitle("Client");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,800);
        f.setVisible(true);
        f.setLayout(null);

        JButton startStop = new JButton("start / stop");
        startStop.setBackground(LIGHTPINK);
        startStop.setBounds(580, 10, 190, 30);
        startStop.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(startStop);

        JPanel dataPanel = new JPanel();
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        dataPanel.setBackground(Color.lightGray);
        dataPanel.setBounds(10, 50, 760, 550);
        f.add(dataPanel);
        dataPanel.setLayout(null);

        JPanel consolePanel = new JPanel();
        consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        consolePanel.setBackground(Color.lightGray);
        consolePanel.setBounds(10, 610, 760, 130);
        f.add(consolePanel);
        consolePanel.setLayout(null);

        JPanel graphPanel = new JPanel();
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        graphPanel.setBackground(LIGHTPINK);
        graphPanel.setBounds(15,15,520,520);
        dataPanel.add(graphPanel);
        graphPanel.setLayout(null);

        JLabel highVal = new JLabel("<html>Highest<br>value:</html>");
        highVal.setBorder(BorderFactory.createLineBorder(Color.black));
        highVal.setBackground(LIGHTBLUE);
        highVal.setBounds(560,15,85,60);
        highVal.setHorizontalAlignment(SwingConstants.CENTER);
        highVal.setOpaque(true);
        dataPanel.add(highVal);

        JTextPane highTxt = new JTextPane();
        highTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        highTxt.setBackground(LIGHTPINK);
        highTxt.setBounds(655,15,85,60);
        dataPanel.add(highTxt);

        JLabel lowVal = new JLabel("<html>Lowest<br>value:</html>");
        lowVal.setBorder(BorderFactory.createLineBorder(Color.black));
        lowVal.setBackground(LIGHTPINK);
        lowVal.setBounds(560,90,85,60);
        lowVal.setHorizontalAlignment(SwingConstants.CENTER);
        lowVal.setOpaque(true);
        dataPanel.add(lowVal);

        JTextPane lowTxt = new JTextPane();
        lowTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        lowTxt.setBackground(LIGHTBLUE);
        lowTxt.setBounds(655,90,85,60);
        dataPanel.add(lowTxt);

        JLabel average = new JLabel("<html>Average</html>");
        average.setBorder(BorderFactory.createLineBorder(Color.black));
        average.setBackground(LIGHTBLUE);
        average.setBounds(560,165,85,60);
        average.setHorizontalAlignment(SwingConstants.CENTER);
        average.setOpaque(true);
        dataPanel.add(average);

        JTextPane avgTxt = new JTextPane();
        avgTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        avgTxt.setBackground(LIGHTPINK);
        avgTxt.setBounds(655,165,85,60);
        dataPanel.add(avgTxt);

        JLabel channels = new JLabel("<html>Channels:</html>");
        channels.setBorder(BorderFactory.createLineBorder(Color.black));
        channels.setBackground(LIGHTPINK);
        channels.setBounds(560,245,85,60);
        channels.setHorizontalAlignment(SwingConstants.CENTER);
        channels.setOpaque(true);
        dataPanel.add(channels);

        channelDropDown = new JComboBox<String>(valuesForDropDown);
        channelDropDown.setVisible(true);
        channelDropDown.setBorder(BorderFactory.createLineBorder(Color.black));
        channelDropDown.setBackground(LIGHTBLUE);
        channelDropDown.setBounds(655,245,85,60);
        dataPanel.add(channelDropDown);

        JLabel frequency = new JLabel("<html>Frequency<br>(Hz):</html>");
        frequency.setBorder(BorderFactory.createLineBorder(Color.black));
        frequency.setBackground(LIGHTBLUE);
        frequency.setBounds(560,325,85,60);
        frequency.setHorizontalAlignment(SwingConstants.CENTER);
        frequency.setOpaque(true);
        dataPanel.add(frequency);

        JTextPane freqTxt = new JTextPane();
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
        client.setVisible(true);
    }
}

