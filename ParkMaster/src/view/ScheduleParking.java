package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

public class ScheduleParking extends JFrame {
    private JTextField parkingSpotTextField;
    private JTextField timeTextField;
    private JComboBox vehicleCombo;
    private JButton scheduleButton;
    private JButton returnButton;
    private JPanel schedulingParkingForm;

    public void initialize() {
        JFrame frame = new JFrame("Schedule Parking");
        frame.setContentPane(new ScheduleParking().schedulingParkingForm);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

}
