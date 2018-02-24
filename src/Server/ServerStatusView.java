/**
 * @SER516 Project2_Team05
 */

package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * ServerStatusView class will create a circle and fill it with color to show the server
 * status
 **/
public class ServerStatusView extends JPanel {
	public boolean running;
	public JLabel statusBlinker;

	private final Color COLOR_OFF = Color.red;
	private final Color COLOR_ON_DIM = new Color(197, 224, 179);
	private final Color COLOR_ON_BRIGHT = new Color(168, 208, 141);
	
	/**
	 * ServerStatusView constructor method will change the status of the server
	 * the method will add a timer so the blinker
	 * statusblinker label will set the background color of the blinker
	 **/
	public ServerStatusView() {
		running = ServerUIMain.ServerRunning;

		Timer timer = new Timer(1000, e2 -> {
			if (running) {
				if (statusBlinker.getForeground() == COLOR_ON_DIM) {
					statusBlinker.setForeground(COLOR_ON_BRIGHT);
				} else {
					statusBlinker.setForeground(COLOR_ON_DIM);
				}
			} else {
				statusBlinker.setForeground(COLOR_OFF);
			}
		});
		timer.start();

		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(new EmptyBorder(8, 8, 8, 8));

		JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.setBackground(ServerUIMain.LIGHTPINK);
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		statusBlinker = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
		statusBlinker.setFont(new Font("Monospaced", Font.PLAIN, 500));
		statusBlinker.setForeground(running ? COLOR_ON_BRIGHT : COLOR_OFF);
		statusPanel.add(statusBlinker, BorderLayout.CENTER);

		this.add(statusPanel, BorderLayout.CENTER);
	}

}
