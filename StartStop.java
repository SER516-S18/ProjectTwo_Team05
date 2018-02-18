import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StartStop extends JFrame implements ActionListener {
	
	private JButton button;
	
public StartStop()
{
	setLayout(null);
	setSize (800,800);
	button = new JButton("Start / Stop");
    Color LIGHTPINK = new Color(255,182,193);
    button.setBackground(LIGHTPINK);
    button.setBounds(580, 10, 190, 30);
    button.setBorder(BorderFactory.createLineBorder(Color.black));
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
