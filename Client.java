import javax.swing.*;// imports all the functions present in the swing library
import java.awt.*;// imports all the functions present in awt library
/**
 * The below class creates a Window Client.
 * @author (Sowmya Madabhushi)
 * @version (2/16/18)
 */
public class Client extends JFrame
{
   public Client()//client class 
   {
       JFrame f = new JFrame(); 
       f.setTitle("Client");
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setSize(800,800);
       f.setVisible(true);
       f.setLayout(new FlowLayout(FlowLayout.CENTER)); 
   }
   public static void main(String[] args) //main 
   {
        Client client = new Client();
        client.setVisible(true);
   }
}