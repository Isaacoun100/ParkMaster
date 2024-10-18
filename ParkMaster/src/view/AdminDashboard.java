package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JButton addANewAdminButton;
    private JButton addANewInspectorButton1;
    private JButton signOutButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel adminDashboardPanel;
    private JPasswordField passwordField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton saveChangesButton;
    private JButton manageParkingSpotsButton;
    private JButton reportsButton;
    private JButton parkConfiguationButton;

    public AdminDashboard() {
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addANewInspectorButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addANewAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setContentPane(new AdminDashboard().adminDashboardPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
