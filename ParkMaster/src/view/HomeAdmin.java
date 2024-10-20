package view;

import controller.jsonParser;
import model.Admin;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeAdmin extends JFrame {
    private JButton logInButton;
    private JPasswordField passwordTextField;
    private JButton returnToHomeButton;
    private JPanel homeAdminPanel;
    private JTextField emailTextField;

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
                try {
                    Admin admin = jsonParser.loginAdmin(
                            passwordTextField.getText(),
                            emailTextField.getText()
                    );

                    if(admin != null){
                        frame.setVisible(false);
                        new AdminDashboard();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Please check your password or email");
                    }

                } catch (IOException ex) {
                    System.out.println("IOException");
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    System.out.println("Cannot find file");
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }
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
