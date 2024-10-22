package view;

import model.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reports extends JFrame {
    private JComboBox comboBox1;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JButton earningTicketsButton;
    private JButton usedSpotsButton;
    private JButton ticketReportButton;
    private JButton selectedSpotsButton;
    private JButton allSpotsButton;
    private JButton returnToDashboardButton;
    private JComboBox comboBox2;
    private JButton parkingReportButton;
    private JButton returnToDashboardButton1;
    private JPanel reportsForm;

    public Reports( Admin admin ) {

        JFrame frame = new JFrame("Reports");
        frame.setContentPane(reportsForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setResizable(false);
        frame.setVisible(true);

        returnToDashboardButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard( admin );
            }
        });
    }

}
