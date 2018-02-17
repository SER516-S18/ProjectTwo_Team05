import javax.swing.*;// imports all the functions present in the swing library
import java.awt.*;// imports all the functions present in awt library
/**
 * The below class creates a Window Server.
 * @author (Venkata Akhil Madaraju)
 * @version (2/16/18)
 */
public class ServerUI extends JFrame
{
	public Color LIGHTBLUE = new Color(173,216,230);
	public Color LIGHTPINK = new Color(255,182,193);
   public ServerUI() 
   {
       JFrame f = new JFrame(); 
       
       f.setTitle("Server");
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setSize(800,800);
       f.setVisible(true);
       f.setLayout(null);
       
       
       JButton startStop = new JButton("start / stop");
       startStop.setBackground(LIGHTPINK);
       startStop.setBounds(580, 10, 190, 30);
       startStop.setBorder(BorderFactory.createLineBorder(Color.black));
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
       
       JPanel signalPanel = new JPanel();
       signalPanel.setBorder(BorderFactory.createLineBorder(Color.black));
       signalPanel.setBackground(LIGHTPINK);
       signalPanel.setBounds(15,15,520,520);
       upPanel.add(signalPanel);
       signalPanel.setLayout(null);
       
       JLabel highVal = new JLabel("<html>Highest<br>value:</html>");
       highVal.setBorder(BorderFactory.createLineBorder(Color.black));
       highVal.setBackground(LIGHTBLUE);
       highVal.setBounds(560,15,85,40);
       highVal.setHorizontalAlignment(SwingConstants.CENTER);
       highVal.setOpaque(true);
       upPanel.add(highVal);
       
       JTextPane highTxt = new JTextPane();
       highTxt.setBorder(BorderFactory.createLineBorder(Color.black));
       highTxt.setBackground(LIGHTPINK);
       highTxt.setBounds(655,15,85,40);
       upPanel.add(highTxt);       
       
       JLabel lowVal = new JLabel("<html>Lowest<br>value:</html>");
       lowVal.setBorder(BorderFactory.createLineBorder(Color.black));
       lowVal.setBackground(LIGHTPINK);
       lowVal.setBounds(560,70,85,40);
       lowVal.setHorizontalAlignment(SwingConstants.CENTER);
       lowVal.setOpaque(true);
       upPanel.add(lowVal);
       
       JTextPane lowTxt = new JTextPane();
       lowTxt.setBorder(BorderFactory.createLineBorder(Color.black));
       lowTxt.setBackground(LIGHTBLUE);
       lowTxt.setBounds(655,70,85,40);
       upPanel.add(lowTxt);  
       
       JLabel frequency = new JLabel("<html>Frequency<br>(Hz):</html>");
       frequency.setBorder(BorderFactory.createLineBorder(Color.black));
       frequency.setBackground(LIGHTBLUE);
       frequency.setBounds(560,125,85,40);
       frequency.setHorizontalAlignment(SwingConstants.CENTER);
       frequency.setOpaque(true);
       upPanel.add(frequency);
       
       JTextPane freqTxt = new JTextPane();
       freqTxt.setBorder(BorderFactory.createLineBorder(Color.black));
       freqTxt.setBackground(LIGHTPINK);
       freqTxt.setBounds(655,125,85,40);
       upPanel.add(freqTxt);
       
       
   }
   public static void main(String[] args) //main 
   {
        ServerUI server = new ServerUI();
        server.setVisible(true);
   }
}
