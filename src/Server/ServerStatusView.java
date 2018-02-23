package Server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServerStatusView extends  JPanel {
    private final Color COLOR_OFF = Color.red;
    private final Color COLOR_ON_DIM = new Color(197, 224, 179);
    private final Color COLOR_ON_BRIGHT = new Color(168,208,141);

    public boolean running;
    public JLabel statusBlinker;

    public ServerStatusView() {
        running = ServerUI.ServerRunning;

        Timer timer = new Timer(1000, e2 -> {
            if(running) {
                if(statusBlinker.getForeground() == COLOR_ON_DIM) {
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
        statusPanel.setBackground(ServerUI.LIGHTPINK);
        statusPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        statusBlinker = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
        statusBlinker.setFont(new Font("Monospaced", Font.PLAIN,  500));
        statusBlinker.setForeground(running ? COLOR_ON_BRIGHT : COLOR_OFF);
        statusPanel.add(statusBlinker, BorderLayout.CENTER);

        this.add(statusPanel, BorderLayout.CENTER);
    }

}
