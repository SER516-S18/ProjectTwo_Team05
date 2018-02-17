import javax.swing.*;// imports all the functions present in the swing library

import java.awt.*;// imports all the functions present in awt library
/**
 * The below class creates a Window Client.
 * @author (Sowmya Madabhushi)
 * @version (2/16/18)
 */
public class ServerUI extends JFrame
{
	public Color LIGHTBLUE = new Color(173,216,230);
	public Color LIGHTPINK = new Color(255,182,193);
   public ServerUI()//client class 
   {
       JFrame f = new JFrame(); 
       
       f.setTitle("Server");
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setSize(800,800);
       f.setVisible(true);
       f.setLayout(new FlowLayout(FlowLayout.CENTER)); 
       
       JButton startStop = new JButton("start / stop");
       startStop.setBackground(LIGHTPINK);
       startStop.setBounds(436, 13, 192, 34);
       startStop.setBorder(BorderFactory.createLineBorder(Color.black));
       f.getContentPane().add(startStop);
   }
   public static void main(String[] args) //main 
   {
        ServerUI server = new ServerUI();
        server.setVisible(true);
   }
}
