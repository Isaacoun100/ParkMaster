package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

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

    public void initialize() {
        JFrame frame = new JFrame("Reports");
        frame.setContentPane(new Reports().reportsForm);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

}
