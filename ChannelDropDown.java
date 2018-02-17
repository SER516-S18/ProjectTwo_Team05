import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ChannelDropDown {

	JFrame temporaryFrame;
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
		
		temporaryFrame.add(channelDropDown);
		temporaryFrame.setVisible(true);
	
	}
	public static void main(String[] args) {
		new ChannelDropDown();
	}
	
}

