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

        JFrame frame = new JFrame("Admin Dashboard");
        frame.setContentPane(adminDashboardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.setResizable(false);
        frame.setVisible(true);

        addANewAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddAdmin();
            }
        });
        addANewInspectorButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddInspector();
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Reports();
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeAdmin();
            }
        });
    }

}
