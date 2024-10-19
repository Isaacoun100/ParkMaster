package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JFrame {
    private JPanel userDashboardForm;
    private JList parkingList;
    private JButton addTimeButton;
    private JButton scheduleParkingButton;
    private JButton finishParkingButton;
    private JButton historyButton;
    private JButton signOutButton;

    public UserDashboard() {

        JFrame frame = new JFrame("Add Admin");
        frame.setContentPane(userDashboardForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,450);
        frame.setResizable(false);
        frame.setVisible(true);

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeUser();
            }
        });
        scheduleParkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ScheduleParking();
            }
        });
    }

}
