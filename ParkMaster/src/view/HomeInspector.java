package view;

import controller.jsonParser;
import model.Admin;
import model.Inspector;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeInspector extends JFrame {
    private JButton logInButton;
    private JPasswordField passwordTextField;
    private JButton returnToHomeButton;
    private JPanel homeInspectorPanel;
    private JTextField emailTextField;

    public HomeInspector() {

        JFrame frame = new JFrame("Login Inspector");
        frame.setContentPane(homeInspectorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Inspector inspector = jsonParser.loginInspector(
                            passwordTextField.getText(),
                            emailTextField.getText()
                    );

                    if(inspector != null){
                        frame.setVisible(false);
                        new InspectorDashboard();
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
