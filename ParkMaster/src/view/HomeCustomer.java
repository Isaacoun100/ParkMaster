package view;

import model.Customer;
import controller.JTool;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeCustomer extends JFrame {
    private JTextField idTextField;
    private JPasswordField passwordFieldText;
    private JButton signInButton;
    private JButton returnToHomeButton;
    private JPanel homeUserPanel;
    private JButton signUpButton;

    public HomeCustomer() {

        JFrame frame = new JFrame("Login User");
        frame.setContentPane(homeUserPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setVisible(true);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Customer customer = JTool.loginCustomer(
                            passwordFieldText.getText(),
                            idTextField.getText()
                    );

                    if(customer != null){
                        frame.setVisible(false);
                        new CustomerDashboard( customer );
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Please check your username or email");
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
                frame.setVisible(false);
                new HomePage();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddCustomer();
            }
        });
    }

}
