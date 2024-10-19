package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class HomePage extends JFrame {
    private JPanel homePagePanel;
    private JButton manageAdminButton;
    private JButton manageUserButton;
    private JButton manageInspectorButton;

    public HomePage() {
        manageAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                HomeAdmin admin = new HomeAdmin();
                admin.setVisible(true);
            }
        });
    }

    public void initialize() {
        JFrame frame = new JFrame("Login Inspector");
        frame.setContentPane(new HomePage().homePagePanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(650,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

}
