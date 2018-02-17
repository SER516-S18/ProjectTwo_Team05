import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StartStop extends JFrame implements ActionListener {
	
	private JButton button;
	
public StartStop()
{
	setLayout(null);
	setSize (300,300);
	button = new JButton("Start / Stop");
	button.addActionListener(this);
	add(button);	
}
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == button) {
		
		
	}
}

public static void main(String args[]) {
	StartStop s = new StartStop();
	s.setVisible(true);
	
}	
}
