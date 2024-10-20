package view;

import javax.swing.*;
import model.Customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboard extends JFrame {
    private JPanel userDashboardForm;
    private JList parkingList;
    private JButton addTimeButton;
    private JButton scheduleParkingButton;
    private JButton finishParkingButton;
    private JButton historyButton;
    private JButton signOutButton;

    public CustomerDashboard() {

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
                new HomeCustomer();
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