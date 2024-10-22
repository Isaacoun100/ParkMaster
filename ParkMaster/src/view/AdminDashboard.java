package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JButton addANewAdminButton;
    private JButton addANewInspectorButton1;
    private JButton signOutButton;
    private JPanel adminDashboardPanel;
    private JButton manageParkingSpotsButton;
    private JButton reportsButton;
    private JButton parkConfiguationButton;
    private JButton manageMyDataButton;


    public AdminDashboard() {

        JFrame frame = new JFrame("Admin Dashboard");
        frame.setContentPane(adminDashboardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,500);
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
        manageParkingSpotsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ManageParking();
            }
        });
    }

}
