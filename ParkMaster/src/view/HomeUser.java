package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUser extends JFrame {
    private JPasswordField emailFieldText;
    private JPasswordField passwordFieldText;
    private JButton signInButton;
    private JButton returnToHomeButton;
    private JPanel homeUserPanel;

    public void initialize() {
        JFrame frame = new JFrame("Login User");
        frame.setContentPane(new HomeUser().homeUserPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public HomeUser() {
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
}
