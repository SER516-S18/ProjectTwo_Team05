import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ChannelDropDown {

	JFrame temporaryFrame;
	JTextField tfrequency = new JTextField();
	JComboBox<String> channelDropDown;
	String[] valuesForDropDown = new String[] {"1", "2", "3", "4", "5"};
	ChannelDropDown(){
		
		temporaryFrame = new JFrame("Temporary Frame");
		temporaryFrame.setLayout(null);
		temporaryFrame.setSize(500, 500);
		temporaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		channelDropDown = new JComboBox<String>(valuesForDropDown);
		channelDropDown.setVisible(true);
		channelDropDown.setBounds(0,0,100,50);
		
		try{
			int frequency = Integer.parseInt(tfrequency.getText());
		}catch(NumberFormatException e){
						
		}
		
		tfrequency.setSize(100,100);
		tfrequency.setBounds(300,300,100,50);
		tfrequency.setVisible(true);
		
		temporaryFrame.add(channelDropDown);
		temporaryFrame.add(tfrequency);
		temporaryFrame.setVisible(true);
	
	}
	public static void main(String[] args) {
		new ChannelDropDown();
	}
	
}

