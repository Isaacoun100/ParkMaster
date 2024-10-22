package view;

import model.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageParking {
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JButton saveChangesButton;
    private JButton returnToDashboardButton;
    private JPanel manageParkingPanel;

    public ManageParking( Admin admin ) {

        JFrame frame = new JFrame("Manage Parking");
        frame.setContentPane(manageParkingPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setResizable(false);
        frame.setVisible(true);

        returnToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard( admin );
            }
        });
        returnToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
