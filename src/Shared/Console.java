package Shared;

import javax.swing.JTextPane;

public class Console {
    
	protected JTextPane consolePanel;

	/**
	 * @param consolePanel
	 */
	public void setConsolePanel(JTextPane consolePanel) {
        this.consolePanel = consolePanel;
    }
	
	 /**
	 * @param inputMsg
	 */
	public void display(String inputMsg) {
	    this.consolePanel.setText("Console:" + inputMsg);
	}
}
