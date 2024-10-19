package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

public class UserDashboard extends JFrame {
    private JPanel userDashboardForm;
    private JList parkingList;
    private JButton addTimeButton;
    private JButton scheduleParkingButton;
    private JButton finishParkingButton;
    private JButton historyButton;
    private JButton signOutButton;

    public void initialize() {
        JFrame frame = new JFrame("Add Admin");
        frame.setContentPane(new UserDashboard().userDashboardForm);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900,450);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

}
