package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

public class InspectorDashboard extends JFrame {
    private JPanel inspectorDashboardPanel;
    private JTextField textField1;
    private JButton checkLicenseButton;
    private JButton signOutButton;

    public void initialize() {
        JFrame frame = new JFrame("Inspector Dashboard");
        frame.setContentPane(new InspectorDashboard().inspectorDashboardPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
}
