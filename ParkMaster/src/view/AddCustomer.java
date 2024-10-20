package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame {
    private JPanel addUserPanel;
    private JTextField nameFieldText;
    private JTextField lastNameFieldText;
    private JFormattedTextField phoneNumberFieldText;
    private JTextField billingAddressFieldText;
    private JTextField emailFieldText;
    private JFormattedTextField idFieldText;
    private JButton createInspectorButton;
    private JButton returnToPreviousMenuButton;
    private JFormattedTextField cardNumberTextField;
    private JFormattedTextField expDateTextField;
    private JFormattedTextField cvvTextField;
    private JTextField registrationPlateTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JPasswordField pinTextField;

    public AddCustomer() {

        JFrame frame = new JFrame("Add User");
        frame.setContentPane(addUserPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setVisible(true);

        createInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(addUserPanel, "Thanks for signing up!");
            }
        });
        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeCustomer();
            }
        });
    }

}
