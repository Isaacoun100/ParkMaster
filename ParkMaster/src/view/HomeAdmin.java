package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class HomeAdmin extends JFrame {
    private JPasswordField emailFieldText;
    private JButton signInButton;
    private JPasswordField passwordFieldText;
    private JButton returnToHomeButton;
    private JPanel homeAdminPanel;

    public HomeAdmin() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        returnToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    public void initialize() {
        JFrame frame = new JFrame("Login Admin");
        frame.setContentPane(new HomeAdmin().homeAdminPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
