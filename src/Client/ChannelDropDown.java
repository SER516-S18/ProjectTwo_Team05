package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
//Class to create a dropdown menu for channel
public class ChannelDropDown {

	private JFrame temporaryFrame;
	private JLabel temporarylabel;
	private JComboBox<String> channelDropDown;
	private String[] valuesForDropDown = new String[] {"1", "2", "3", "4", "5"};
	ChannelDropDown(){
		
		//Temporary frame container for dropdown
		temporaryFrame = new JFrame("Temporary Frame");
		temporaryFrame.setLayout(null);
		temporaryFrame.setSize(500, 500);
		temporaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Temporary label to view selected values from dropdown
		temporarylabel = new JLabel("1");
		temporarylabel.setBounds(100, 0, 100, 50);
		temporarylabel.setVisible(true);
		
		channelDropDown = new JComboBox<String>(valuesForDropDown);
		channelDropDown.setVisible(true);
		channelDropDown.setBounds(0,0,100,50);
		
		//Action Listener invoked when an action is performed on the dropdown list
		channelDropDown.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String)channelDropDown.getSelectedItem();
				temporarylabel.setText(selectedValue);
				
			}
		});
		
		temporaryFrame.add(channelDropDown);
		temporaryFrame.add(temporarylabel);
		temporaryFrame.setVisible(true);
	
	}
	
	
}

