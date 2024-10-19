package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage extends JFrame {
    private JPanel homePagePanel;
    private JButton manageAdminButton;
    private JButton manageUserButton;
    private JButton manageInspectorButton;

    public HomePage() {

        JFrame frame = new JFrame("Park Master");
        frame.setContentPane(homePagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,600);
        frame.setResizable(false);
        frame.setVisible(true);

        manageAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeAdmin();
            }
        });
        manageUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeUser();
            }
        });
        manageInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeInspector();
            }
        });
    }
}
