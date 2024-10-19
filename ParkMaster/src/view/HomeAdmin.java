package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin extends JFrame {
    private JPasswordField emailFieldText;
    private JButton logInButton;
    private JPasswordField passwordFieldText;
    private JButton returnToHomeButton;
    private JPanel homeAdminPanel;
    private JButton signUpButton;

    public HomeAdmin() {

        JFrame frame = new JFrame("Login Admin");
        frame.setContentPane(homeAdminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard();
            }
        });
        returnToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomePage();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddUser();
            }
        });
    }

}
