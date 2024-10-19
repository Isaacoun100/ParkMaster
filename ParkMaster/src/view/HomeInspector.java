package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class HomeInspector extends JFrame {
    private JButton signInButton;
    private JPasswordField emailFieldText;
    private JPasswordField passwordFieldText;
    private JButton returnToHomeButton;
    private JPanel homeInspectorPanel;

    public HomeInspector() {

        JFrame frame = new JFrame("Login Inspector");
        frame.setContentPane(homeInspectorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new InspectorDashboard();
            }
        });
        returnToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomePage();
            }
        });
    }

}
