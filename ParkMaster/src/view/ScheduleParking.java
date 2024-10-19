package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleParking extends JFrame {
    private JTextField parkingSpotTextField;
    private JTextField timeTextField;
    private JComboBox vehicleCombo;
    private JButton scheduleButton;
    private JButton returnButton;
    private JPanel scheduleParkingPanel;

    public ScheduleParking() {

        JFrame frame = new JFrame("Schedule Parking");
        frame.setContentPane(scheduleParkingPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(scheduleParkingPanel, "Parking Scheduled Successfully");
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserDashboard();
            }
        });
    }

}
