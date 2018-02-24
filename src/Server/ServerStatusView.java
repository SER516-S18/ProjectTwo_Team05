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

import Shared.Constant;

/**
 * ServerStatusView class will create a circle and fill it with color to show
 * the server status
 **/
public class ServerStatusView extends JPanel {
	private static final long serialVersionUID = 1L;
	public boolean running;
	public JLabel statusBlinker;

	/**
	 * ServerStatusView constructor method will change the status of the server the
	 * method will add a timer so the blinker status blinker label will set the
	 * background color of the blinker
	 **/
	public ServerStatusView() {
		running = ServerUIMain.serverRunning;

		Timer timer = new Timer(1000, e2 -> {
			if (running) {
				if (statusBlinker.getForeground() == Constant.COLORONDIM) {
					statusBlinker.setForeground(Constant.COLORONBRIGHT);
				} else {
					statusBlinker.setForeground(Constant.COLORONDIM);
				}
			} else {
				statusBlinker.setForeground(Color.red);
			}
		});
		timer.start();

		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(new EmptyBorder(8, 8, 8, 8));

		JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.setBackground(Constant.LIGHTPINK);
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		statusBlinker = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
		statusBlinker.setFont(new Font("Monospaced", Font.PLAIN, 500));
		statusBlinker.setForeground(running ? Constant.COLORONBRIGHT : Color.red);
		statusPanel.add(statusBlinker, BorderLayout.CENTER);

		this.add(statusPanel, BorderLayout.CENTER);
	}

}
