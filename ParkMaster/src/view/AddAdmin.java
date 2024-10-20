package view;

import controller.jsonParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class AddAdmin extends JFrame {
    private JPanel addAdminPanel;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField billingAddressTextField;
    private JFormattedTextField hireDateTextField;
    private JPasswordField pinTextField;
    private JFormattedTextField idTextField;
    private JButton createAdmin;
    private JButton returnToPreviousMenuButton;

    public AddAdmin() {

        JFrame frame = new JFrame("Add Admin");
        frame.setContentPane(addAdminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,450);
        frame.setResizable(false);
        frame.setVisible(true);

        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard();
            }
        });
        createAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    jsonParser.addAdmin(
                            nameTextField.getText(),
                            lastNameTextField.getText(),
                            phoneNumberTextField.getText(),
                            emailTextField.getText(),
                            billingAddressTextField.getText(),
                            idTextField.getText(),
                            pinTextField.getText(),
                            LocalDate.parse(hireDateTextField.getText())
                    );
                    JOptionPane.showMessageDialog(addAdminPanel, "Admin added successfully");
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(addAdminPanel, "Unable to add admin, please check the information");
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
