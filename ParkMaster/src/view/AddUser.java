package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddUser extends JFrame {
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

    public AddUser() {
        createInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void initialize() {
        JFrame frame = new JFrame("Add User");
        frame.setContentPane(new AddUser().addUserPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

}
