import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class textBox extends JFrame
{
       JPanel jp = new JPanel();
       JLabel jl = new JLabel();
       JTextField jt = new JTextField(30);
       JButton jb = new JButton("Click!");

       public textBox()
       {
              setTitle("textBox");
              setVisible(true);
              setSize(400, 200);
              setDefaultCloseOperation(EXIT_ON_CLOSE);

              jp.add(jt);


              jt.addActionListener(new ActionListener()
              {
                     public void actionPerformed(ActionEvent e)
                     {
                           String input = jt.getText();
                           jl.setText(input); 
                     }
              });

              jp.add(jb);
              jb.addActionListener(new ActionListener()
              {
                      public void actionPerformed(ActionEvent e)
                      {
                             String input = jt.getText();
                             jl.setText(input);
                      }
              });
              
              jp.add(jl);
              add(jp);	

       }

       public static void main(String[] args)
       {
    	   textBox t = new textBox();
       }
}
