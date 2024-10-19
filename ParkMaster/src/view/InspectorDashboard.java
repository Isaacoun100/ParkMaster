package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InspectorDashboard extends JFrame {
    private JPanel inspectorDashboardPanel;
    private JTextField textField1;
    private JButton checkLicenseButton;
    private JButton signOutButton;

    public InspectorDashboard() {

        JFrame frame = new JFrame("Inspector Dashboard");
        frame.setContentPane(inspectorDashboardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);
        frame.setVisible(true);

        checkLicenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(inspectorDashboardPanel, "License Checked!");
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeInspector();
            }
        });
    }
}
